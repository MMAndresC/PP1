package com.svalero.javafx.tasks;

import com.google.gson.reflect.TypeToken;
import com.svalero.javafx.models.Client;
import com.svalero.javafx.models.Gardener;
import javafx.concurrent.Task;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;

import com.google.gson.Gson;

public class ListTask extends Task<Void> {
    private final String entity;
    public ListTask(String entity){
        this.entity = entity;
    }

    @Override
    protected Void call() throws Exception{
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/" + this.entity ))
                    .GET()
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        Gson gson = new Gson();
                        if(this.entity.equals("clients")){
                            Type listType = new TypeToken<List<Client>>() {}.getType();
                            List<Client> clients = gson.fromJson(response, listType);
                            clients.forEach(System.out::println);
                        }else if(this.entity.equals("gardeners")){
                            Type listType = new TypeToken<List<Gardener>>() {}.getType();
                            List<Client> gardeners = gson.fromJson(response, listType);
                            gardeners.forEach(System.out::println);
                        }




                       /* Platform.runLater(() -> {
                            label.setText("Título: " + post.getTitle() + "\nCuerpo: " + post.getBody());
                        });*/
                    })
                    .exceptionally(e -> {
                     /*   Platform.runLater(() -> {
                            label.setText("Error: " + e.getMessage());
                        });*/
                        return null;
                    });

        }
        return null;
    }



}
