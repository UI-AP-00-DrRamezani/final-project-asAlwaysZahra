package com.example.demo3.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scores extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo3/Scores.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ScoresController controller = fxmlLoader.getController();
        controller.fillList();

        stage.setTitle("Scores");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
