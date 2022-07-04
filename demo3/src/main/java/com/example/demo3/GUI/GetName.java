package com.example.demo3.GUI;

import com.example.demo3.DataBase;
import com.example.demo3.Player;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GetName extends Application {

    @FXML
    TextField name;
    @FXML
    TextField id;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo3/GetName.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("name gir");
        stage.setScene(scene);
        stage.show();

    }

    public void OKClicked() throws SQLException, IOException {

        DataBase.readPlayers();

        if (!name.getText().isEmpty() && !id.getText().isEmpty()) {

            for (Player p : Player.allPlayers)
                if (name.getText().equals(p.getName())) {
                    Player.playing = p;
                    new GameGround(true).start((Stage) name.getScene().getWindow());
                    return;
                }

            Player.playing = new Player(Integer.parseInt(id.getText()), name.getText());
            DataBase.insertPlayer(Player.playing);
            new GameGround(true).start((Stage) name.getScene().getWindow());

        } else {
            new Alert(Alert.AlertType.ERROR, "Enter your name and id").show();
        }
    }
}
