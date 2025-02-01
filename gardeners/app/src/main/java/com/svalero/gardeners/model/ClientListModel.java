package com.svalero.gardeners.model;

import com.svalero.gardeners.api.ClientsApi;
import com.svalero.gardeners.api.ClientsApiInterface;
import com.svalero.gardeners.contract.ClientListContract;
import com.svalero.gardeners.domain.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientListModel implements ClientListContract.Model {

    @Override
    public void loadClients (OnLoadClientsListener listener) {
        ClientsApiInterface clientsApi = ClientsApi.buildInstance();
        Call<ArrayList<Client>> getClientsCall = clientsApi.getClients();
        getClientsCall.enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<ArrayList<Client>> call, Response<ArrayList<Client>> response) {
                if (response.code() == 200) {
                    listener.onLoadClientsSuccess(response.body());
                } else if (response.code() == 500) {
                    listener.onLoadClientsError("The API is not available.  Please try again");
                } else {
                    listener.onLoadClientsError(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Client>> call, Throwable t) {
                listener.onLoadClientsError("Could not connect to the data source.  Please check the connection and try again.");
            }
        });
    }
}
