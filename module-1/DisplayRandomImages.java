//JuedejaRichard_Module1_8-17-25
//Write a JavaFX program that displays four images randomly selected from a deck of 52 cards.

package com.example.displayrandomimages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class DisplayRandomImages extends Application {

    String folderPath = "C:/Users/Lordy/IdeaProjects/DisplayRandomImages/Cards/";

    @Override
    public void start(Stage stage){

        File folder = new File(folderPath);
        File[] imagesFiles = folder.listFiles((dir, name) -> name.endsWith(".jpg"));

        //create a button to display 4 new images when clicked
        Button buttonRefresh = new Button("Refresh");
        buttonRefresh.setOnAction(e -> {
        //Randomizes images
            Random random = new Random();
            File randomImages = imagesFiles[random.nextInt(imagesFiles.length)];
        //loads images
            Image cardImage = new Image(randomImages.toURI().toString());
            ImageView imageView = new ImageView(cardImage);

            imageView.setFitWidth(200);
        //creates scene
        StackPane root = new StackPane();

        FXMLLoader fxmlLoader = new FXMLLoader(DisplayRandomImages.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Showing random images");
        stage.setScene(scene);
        stage.show();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}