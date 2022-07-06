package com.example.demo3.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Lose extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo3/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Students vs TAs");
        stage.setScene(scene);
        stage.show();
    }
}
