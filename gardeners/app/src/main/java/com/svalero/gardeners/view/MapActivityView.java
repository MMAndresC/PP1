package com.svalero.gardeners.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.gardeners.R;
import com.svalero.gardeners.domain.Client;
import com.svalero.gardeners.util.MapUtil;

import java.util.ArrayList;

public class MapActivityView extends AppCompatActivity implements Style.OnStyleLoaded {

    private ArrayList<Client> clientList;
    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        clientList = intent.getParcelableArrayListExtra("clientList");

        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
        pointAnnotationManager = MapUtil.initializePointAnnotationManager(mapView);

    }

    private void viewClients() {
        for (Client client : clientList) {
            addMarker(client.getName(), client.getLatitude(), client.getLongitude());
        }
    }

    private void addMarker(String message, double latitude, double longitude) {
        PointAnnotationOptions marker = new PointAnnotationOptions()
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withTextField(message)
                .withPoint(Point.fromLngLat(longitude, latitude));
        pointAnnotationManager.create(marker);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {
        viewClients();
    }


}