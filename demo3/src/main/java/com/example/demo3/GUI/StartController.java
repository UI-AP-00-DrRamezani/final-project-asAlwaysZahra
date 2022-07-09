package com.example.demo3.GUI;

import com.example.demo3.DataBase;
import com.example.demo3.Player;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class StartController {

    @FXML
    ListView<String> scoreBoard;

    public void fillList() {
        DataBase.readPlayers();
        int i = 1;
        for (Player p : Player.allPlayers)
            scoreBoard.getItems().add(i++ + "   -   " + p.getName() + " : " + p.getScore());
    }

    public void newGame() throws SQLException, IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        scoreBoard.getScene().getWindow().hide();
        new GameGround(true).start(stage);
    }

    public void oldGame() throws SQLException, IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        scoreBoard.getScene().getWindow().hide();
        new GameGround(false).start(stage);
    }

    public void exit() {
        System.exit(0);
    }
}
