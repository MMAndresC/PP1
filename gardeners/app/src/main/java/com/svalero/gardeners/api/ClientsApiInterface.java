package com.svalero.gardeners.api;

import com.svalero.gardeners.domain.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClientsApiInterface {

    @GET("clients")
    Call<ArrayList<Client>> getClients();

    @GET("clients/{id}")
    Call<Client> getClient(@Path("id") int id);

    @POST("clients")
    Call<Client> addClient(@Path("userId") long userId, @Body Client client);

}
