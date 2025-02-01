package com.svalero.javafx.tasks;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Random;
import javafx.embed.swing.SwingFXUtils;

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
                BufferedImage bufferedImage = toGrayScale(byteArrayInputStream);
                if(bufferedImage == null)
                    throw new Exception("Error changing image format");
                showImage(bufferedImage);
            } else {
                System.out.println("Error getting image: " + response.statusCode());
                throw new Exception("Error getting image");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private BufferedImage toGrayScale(ByteArrayInputStream byteArrayInputStream) throws IOException {
        try {
            BufferedImage image = ImageIO.read(byteArrayInputStream);
            int width = image.getWidth();
            int height = image.getHeight();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {

                    int[] rgb = image.getRaster().getPixel(i, j, new int[3]);

                    int red = rgb[0];
                    int green = rgb[1];
                    int blue = rgb[2];

                    int avg = (red + green + blue) / 3;

                    int[] newRgb = { avg, avg, avg };

                    image.getRaster().setPixel(i, j, newRgb);
                }
            }
            return image;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void showImage(BufferedImage bufferedImage){
        ImageView imageView = createImageViewFromBufferedImage(bufferedImage, 900, 500);
        System.out.println(imageView);
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

    public ImageView createImageViewFromBufferedImage(BufferedImage bufferedImage, int width, int height){
        ImageView imageView = new ImageView();
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView.setImage(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }
}
