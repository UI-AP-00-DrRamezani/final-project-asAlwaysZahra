package com.example.demo3.GUI;

import com.example.demo3.DataBase;
import com.example.demo3.Player;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ScoresController {
    @FXML
    ListView<String> list;

    public void fillList() {

        DataBase.readPlayers();
//        Collections.sort(Player.allPlayers);

        for (Player p : Player.allPlayers)
            list.getItems().add(p.getId() + " - " + p.getName() + "    :    " + p.getScore());

    }
}
