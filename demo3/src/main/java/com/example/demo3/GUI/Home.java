package com.example.demo3.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Home extends Application {

    @FXML
    Button btn;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo3/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Students vs TAs");
        stage.setScene(scene);
        stage.show();
    }

    public void startGame() throws SQLException, IOException {
//        new GameGround(true).start((Stage) btn.getScene().getWindow());
        new GetName().start((Stage) btn.getScene().getWindow());
    }

    public void lastGame() throws SQLException, IOException {
        new GameGround(false).start((Stage) btn.getScene().getWindow());
    }

    public void scores() throws IOException {
        new Scores().start((Stage) btn.getScene().getWindow());
    }

    public static void main(String[] args) {
        launch();
    }
}