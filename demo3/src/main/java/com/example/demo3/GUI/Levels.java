package com.example.demo3.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Levels extends Application {

    @FXML
    Button level1;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo3/Levels.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Levels");
        stage.setScene(scene);
        stage.show();
    }

    public void level1() throws SQLException, IOException {
        new Level1(true).start((Stage) level1.getScene().getWindow());
    }

    public void level2() throws SQLException, IOException {
        new Level2(true).start((Stage) level1.getScene().getWindow());
    }

    public void level3() throws SQLException, IOException {
        new Level2(true).start((Stage) level1.getScene().getWindow());
    }
}
