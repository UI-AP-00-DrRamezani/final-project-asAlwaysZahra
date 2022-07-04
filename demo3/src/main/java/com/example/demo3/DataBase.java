package com.example.demo3;

import com.example.demo3.Heros.Hero;

import java.sql.*;

public class DataBase {

    public static void saveGame(Hero h) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gameDataBase", "root", "")) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = String.format("INSERT INTO heroes (type, x, y, power, speed, health, officeHealth) " +
                            "VALUES ('%s', '%f', '%f', '%d', '%d', '%d', '%d')",
                    h.getClass(), h.getTranslateX(), h.getTranslateY(), h.getPower(), h.getSpeed(), h.getHealth(), ProfessorOffice.health);

            Statement s = connection.prepareStatement(sql);
            s.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteData() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gameDataBase", "root", "")) {

            String sql = "DELETE FROM `heroes` WHERE 1=1";
            Statement s = connection.prepareStatement(sql);
            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet loadData() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gameDataBase", "root", "");

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `heroes`";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            return rs;

        } catch (Exception e) {
            System.out.println("error in load data:");
            e.printStackTrace();
            return null;
        }
    }

    public static void insertPlayer(Player p) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gameDataBase", "root", "")) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = String.format("INSERT INTO playersScores (playerID, name, score, rank) " +
                            "VALUES ('%d', '%s', '%d', '%d')",
                    p.getId(), p.getName(), p.getScore(), p.getScore());

            Statement s = connection.prepareStatement(sql);
            s.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updatePlayer(Player p) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gameDataBase", "root", "")) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = String.format("UPDATE `playersScores` SET score='%d' WHERE playerID='%d'", p.getScore(), p.getId());
            Statement s = connection.prepareStatement(sql);
            s.execute(sql);

        } catch (Exception e) {
            System.out.println("error in update player:");
            e.printStackTrace();
        }
    }

    public static void readPlayers() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/gameDataBase", "root", "")) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM `playersScores` ORDER BY playersscores.score DESC;";
            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            Player.allPlayers.clear();

            while (rs.next()) {

                int id = rs.getInt("playerID");
                String name = rs.getString("name");
                int score = rs.getInt("score");
                int rank = rs.getInt("rank");
                Player p = new Player(id, name);
                p.setScore(score);
                p.setRank(rank);

                Player.allPlayers.add(p);

            }

        } catch (Exception e) {
            System.out.println("error in read player:");
            e.printStackTrace();
        }
    }
}
