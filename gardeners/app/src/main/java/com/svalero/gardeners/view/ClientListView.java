package com.svalero.gardeners.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.gardeners.R;
import com.svalero.gardeners.adapter.ClientAdapter;
import com.svalero.gardeners.contract.ClientListContract;
import com.svalero.gardeners.domain.Client;
import com.svalero.gardeners.presenter.ClientListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ClientListView extends AppCompatActivity implements ClientListContract.View {

    private ClientAdapter clientAdapter;
    private ArrayList<Client> clientList;
    private ClientListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new ClientListPresenter(this);

        clientList = new ArrayList<>();

        RecyclerView clientsView = findViewById(R.id.clients_view);
        clientsView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        clientsView.setLayoutManager(linearLayoutManager);

        clientAdapter = new ClientAdapter(clientList);
        clientsView.setAdapter(clientAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clientList.clear();
        presenter.loadClients();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_map) {
            Intent intent = new Intent(this, MapActivityView.class);
            intent.putParcelableArrayListExtra("clientList", clientList);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_register_client) {
            Intent intent = new Intent(this, RegisterClientView.class);
            startActivity(intent);
        }
        return true;
    }

    public void registerClient(View view) {
        Intent intent = new Intent(this, RegisterClientView.class);
        startActivity(intent);
    }

    @Override
    public void listClients(List<Client> clientList) {
        this.clientList.addAll(clientList);
        clientAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}