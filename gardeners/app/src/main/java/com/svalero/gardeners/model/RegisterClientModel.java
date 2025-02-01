package com.svalero.gardeners.model;

import com.svalero.gardeners.api.ClientsApi;
import com.svalero.gardeners.api.ClientsApiInterface;
import com.svalero.gardeners.contract.RegisterClientContract;
import com.svalero.gardeners.domain.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterClientModel implements RegisterClientContract.Model {
    @Override
    public void registerClient(Client client, OnRegisterClientListener listener) {
        ClientsApiInterface bikesApi = ClientsApi.buildInstance();
        Call<Client> callRegisterBike = bikesApi.addClient(1, client);
        callRegisterBike.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                switch (response.code()) {
                    case 201:
                        listener.onRegisterClientSuccess(response.body());
                        break;
                    case 400:
                        listener.onRegisterClientError("Error validating request: " + response.message());
                        break;
                    case 500:
                        listener.onRegisterClientError("Internal API error: " + response.message());
                        break;
                    default:
                        listener.onRegisterClientError("API invocation error: " + response.message());
                        break;


                }

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                listener.onRegisterClientError("Unable to connect to the data source.  Check the connection and try again.");

            }
        });
    }
}
