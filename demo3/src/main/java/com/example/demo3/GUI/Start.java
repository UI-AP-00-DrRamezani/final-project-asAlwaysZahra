package com.example.demo3.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class Start extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo3/Start.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        stage.initStyle(StageStyle.UNDECORATED);

        StartController controller = fxmlLoader.getController();
        controller.fillList();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
