package com.example.demo3.GUI;

import com.example.demo3.DataBase;
import com.example.demo3.Player;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class WinPage extends Application {

    @FXML
    TextField name;
    @FXML
    TextField id;
    @FXML
    Label lbl_score;


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo3/WinPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

    }

    public void OKClicked() throws SQLException, IOException {

        DataBase.readPlayers();

        if (!name.getText().isEmpty() && !id.getText().isEmpty()) {

            for (Player p : Player.allPlayers)
                if (name.getText().equals(p.getName())) {
                    Player.playing = p;

                    // update player
                    Player.playing.setScore(Integer.parseInt(lbl_score.getText()) + Player.playing.getScore());
                    DataBase.updatePlayer(Player.playing);
                    return;
                }

            Player.playing = new Player(Integer.parseInt(id.getText()), name.getText());
            Player.playing.setScore(Integer.parseInt(lbl_score.getText()));
            DataBase.insertPlayer(Player.playing);

        } else {
            new Alert(Alert.AlertType.ERROR, "Enter your name and id").show();
        }

        // back to level page
        new Start().start((Stage) id.getScene().getWindow());
    }
}
