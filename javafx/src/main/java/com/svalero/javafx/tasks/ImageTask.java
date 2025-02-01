package com.svalero.javafx.tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.http.*;
import java.util.Random;

public class ImageTask extends Task<Void> {
    private final int TOTAL_IMAGES = 3;
    private final TabPane tabPane;
    private final String BASE_URL = "http://localhost:8080/api/v1/";

    public ImageTask(TabPane tabPane){
        this.tabPane = tabPane;
    }
    protected Void call() throws Exception{

            //Get random number between 1-3
            Random random = new Random();
            //range 0-2
            int num = random.nextInt(3);
            //+1 to change range 1-3
            num +=1;
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "images/" + num))
                    .build();

        try {
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            if (response.statusCode() == 200) {
                byte[] imageBytes = response.body();

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
                showImage(byteArrayInputStream);
            } else {
                System.out.println("Error al obtener la imagen: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void showImage(ByteArrayInputStream byteArrayInputStream){
        Image image = new Image(byteArrayInputStream,
                900,
                500,
                true,
                true
        );
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(900);
        imageView.setFitHeight(500);

        Tab imageTab = new Tab("Image");
        imageTab.setClosable(true);

        Platform.runLater(() -> {
            try{
                imageTab.setContent(imageView);
                tabPane.getTabs().add(imageTab);
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
