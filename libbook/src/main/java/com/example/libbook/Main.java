package com.example.libbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(Main.class.getResource("Hello.fxml"));
       stage.setTitle("LibBook");
       stage.setScene(new Scene(root,700,400));
       stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}