package com.example.demo3;

import java.util.ArrayList;

public class Player implements Comparable<Player> {

    public static Player playing;
    public static ArrayList<Player> allPlayers = new ArrayList<>();
    private int id;
    private String name;
    private int score;
    private int rank;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(this.score, o.score);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", rank=" + rank +
                '}';
    }

    // Getters and Setters ================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
