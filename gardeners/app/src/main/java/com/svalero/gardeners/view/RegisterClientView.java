package com.svalero.gardeners.view;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.mapbox.maps.plugin.gestures.OnMapClickListener;
import com.svalero.gardeners.R;
import com.svalero.gardeners.contract.RegisterClientContract;
import com.svalero.gardeners.domain.Client;
import com.svalero.gardeners.presenter.RegisterClientPresenter;
import com.svalero.gardeners.util.MapUtil;

import java.text.ParseException;

public class RegisterClientView extends AppCompatActivity implements RegisterClientContract.View, Style

        .OnStyleLoaded, OnMapClickListener {

    private RegisterClientPresenter presenter;
    private MapView mapView;
    private AnnotationManager pointAnnotationManager;
    private GesturesPlugin gesturesPlugin;
    private Point currentPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        presenter = new RegisterClientPresenter(this);

        initializeMapView();
        pointAnnotationManager = MapUtil.initializePointAnnotationManager(mapView);
        initializeGesturesPlugin();

    }

    public void register(View view) {
        if (currentPoint == null) {
            Toast.makeText(this, "You must select a location to register your garden", +Toast.LENGTH_LONG).show();
            return;
        }
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        int gardenSize = Integer.parseInt(((EditText) findViewById(R.id.garden_size)).getText().toString());
        Client client = new Client(name, phone, gardenSize, currentPoint.latitude(), currentPoint.longitude());
        presenter.registerClient(client);

    }
    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(findViewById(R.id.add_client_button), message, BaseTransientBottomBar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Snackbar.make(findViewById(R.id.add_client_button), message, BaseTransientBottomBar.LENGTH_SHORT).show();

    }

    private void initializeMapView() {
        mapView = findViewById(R.id.registerMapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS, this);
    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }

    private void initializeGesturesPlugin() {
        gesturesPlugin = GesturesUtils.getGestures(mapView);
        gesturesPlugin.addOnMapClickListener(this);
    }

    private void addMarker(double latitude, double longitude) {
        PointAnnotationOptions marker = new PointAnnotationOptions()
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker))
                .withPoint(Point.fromLngLat(longitude, latitude));
        pointAnnotationManager.create(marker);
    }

    @Override
    public boolean onMapClick(@NonNull Point point) {
        pointAnnotationManager.deleteAll();
        currentPoint = point;
        addMarker(point.latitude(), point.longitude());
        return true;
    }
}