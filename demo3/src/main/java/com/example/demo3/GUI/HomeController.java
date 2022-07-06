package com.example.demo3.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HomeController {

    @FXML
    Button btn;

    public void lastGame() throws SQLException, IOException {
        new GameGround(false).start((Stage) btn.getScene().getWindow());
    }

    public void scores() throws IOException {
        new Scores().start((Stage) btn.getScene().getWindow());
    }

    public void startGame() throws SQLException, IOException {
//        new GameGround(true).start((Stage) btn.getScene().getWindow());
        new Levels().start((Stage) btn.getScene().getWindow());
//        new Levels().start(new Stage());
    }


}
