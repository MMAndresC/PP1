package com.svalero.gardeners.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.gardeners.R;

public class DetailActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();
        long clientId = intent.getLongExtra("clientId", 0);
        printClient(clientId);

    }

    private void printClient(long clientId) {
        ((TextView) findViewById(R.id.client_id)).setText(String.valueOf(clientId));
    }

}