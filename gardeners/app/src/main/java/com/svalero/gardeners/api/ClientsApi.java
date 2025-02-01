package com.svalero.gardeners.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientsApi {
    private static final String URL = "http://172.17.21.31:8080/api/v1/";
    public static ClientsApiInterface buildInstance() {

        Gson gson = new GsonBuilder()
                //.setDateFormat("yyyy-MM-dd")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ClientsApiInterface.class);

    }
}
