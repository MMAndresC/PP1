package com.svalero.javafx.tasks;

import com.google.gson.reflect.TypeToken;
import com.svalero.javafx.models.Client;
import com.svalero.javafx.models.Gardener;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;

import com.google.gson.Gson;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListTask extends Task<Void> {
    private final String entity;
    private final TabPane tabPane;

    public ListTask(String entity, TabPane tab){
        this.entity = entity;
        this.tabPane = tab;
    }

    @Override
    protected Void call() throws Exception{
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/" + this.entity ))
                    .GET()
                    .build();
            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        Gson gson = new Gson();
                        try {
                            if (this.entity.equals("clients")) {
                                Type listType = new TypeToken<List<Client>>() {
                                }.getType();
                                List<Client> clients = gson.fromJson(response, listType);
                                createClientTableView(clients);
                            } else if (this.entity.equals("gardeners")) {
                                Type listType = new TypeToken<List<Gardener>>() {
                                }.getType();
                                List<Gardener> gardeners = gson.fromJson(response, listType);
                                createGardenerTableView(gardeners);
                            }
                        }catch (Exception e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }
                    })
                    .exceptionally(e -> {
                        return null;
                    });

        }
        return null;
    }

    private void createClientTableView(List<Client> clients){
        TableView<Client> tableView = new TableView<>();

        TableColumn<Client, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Client, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Client, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Client, Integer> gardenSizeColumn = new TableColumn<>("Garden Size");
        gardenSizeColumn.setCellValueFactory(new PropertyValueFactory<>("gardenSize"));

        TableColumn<Client, Boolean> vipColumn = new TableColumn<>("VIP");
        vipColumn.setCellValueFactory(new PropertyValueFactory<>("vip"));

        TableColumn<Client, Double> latitudeColumn = new TableColumn<>("Latitude");
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));

        TableColumn<Client, Double> longitudeColumn = new TableColumn<>("Longitude");
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitude"));

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(phoneColumn);
        tableView.getColumns().add(addressColumn);
        tableView.getColumns().add(gardenSizeColumn);
        tableView.getColumns().add(vipColumn);
        tableView.getColumns().add(latitudeColumn);
        tableView.getColumns().add(longitudeColumn);
        ObservableList<Client> observableClients = FXCollections.observableArrayList(clients);

        tableView.setItems(observableClients);

        Tab clientTab = new Tab(this.entity);
        clientTab.setClosable(true);

        Platform.runLater(() -> {
            try{
                clientTab.setContent(tableView);
                tabPane.getTabs().add(clientTab);
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void createGardenerTableView(List<Gardener> gardeners){
        TableView<Gardener> tableView = new TableView<>();
        TableColumn<Gardener, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Gardener, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Gardener, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Gardener, String> hireDateColumn = new TableColumn<>("Hire Date");
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        TableColumn<Gardener, Boolean> selfEmployedColumn = new TableColumn<>("Self Employed");
        selfEmployedColumn.setCellValueFactory(new PropertyValueFactory<>("selfEmployed"));

        TableColumn<Gardener, String> licensePlateColumn = new TableColumn<>("License Plate");
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));


        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(phoneColumn);
        tableView.getColumns().add(addressColumn);
        tableView.getColumns().add(hireDateColumn);
        tableView.getColumns().add(selfEmployedColumn);
        tableView.getColumns().add(licensePlateColumn);

        ObservableList<Gardener> observableGardeners = FXCollections.observableArrayList(gardeners);

        tableView.setItems(observableGardeners);

        Tab gardenerTab = new Tab(this.entity);
        gardenerTab.setClosable(true);

        Platform.runLater(() -> {
            try{
                gardenerTab.setContent(tableView);
                tabPane.getTabs().add(gardenerTab);
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }

}
