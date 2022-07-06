package com.example.demo3.GUI;

import com.example.demo3.DataBase;
import com.example.demo3.Heros.Hero;
import com.example.demo3.Heros.StudentHeros.FastStudent;
import com.example.demo3.Heros.StudentHeros.NormalStudent;
import com.example.demo3.Heros.StudentHeros.SmartStudent;
import com.example.demo3.Heros.TAHeros.HeadTA;
import com.example.demo3.Heros.TAHeros.RobotTA;
import com.example.demo3.Heros.TAHeros.TiredTA;
import com.example.demo3.ProfessorOffice;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Level3 extends Application {

    int startPoint = 1300; // x axis
    int line1 = 100 - 10; // y axis
    int line2 = 200 - 10;
    int line3 = 300 - 10;
    int line4 = 400 - 10;
    int line5 = 500 - 10;

    public static AnchorPane bg;
    Group studentHeroesGroup;
    public static ArrayList<Hero> allStudents = new ArrayList<>();
    public static ArrayList<Hero> allTAs = new ArrayList<>();
    GameController controller;
    //    public static boolean continueGame = true;
    public static boolean newGame = true;
    int score = 0;
    int numberOfTAs = 15;

    ArrayList<Timeline> timelines = new ArrayList<>();
    ArrayList<ProgressIndicator> progressIndicators = new ArrayList<>();

    public Level3(boolean newGame) {
        Level1.newGame = newGame;
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo3/GameGround.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("");

        controller = fxmlLoader.getController();
        bg = controller.bg;

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        stage.getScene().getWindow().setOnCloseRequest(windowEvent -> {
            // add heroes
            DataBase.deleteData();
            for (Hero h : allTAs)
                DataBase.saveGame(h);
            for (Hero h : allStudents)
                DataBase.saveGame(h);
        });


        dragHeroes();


        if (newGame)
            newGame();
        else
            oldGame();


        if (numberOfTAs > 0) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), e -> {
                updateGame();
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

        } else {
            System.out.println("!11");
            try {
                new Win().start(new Stage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
//                new Alert(Alert.AlertType.INFORMATION, "YOU WON!").show();
//                endGameTAs();
        }
        if (ProfessorOffice.health <= 0)
            new Alert(Alert.AlertType.INFORMATION, "YOU LOST!").show();

    }

    public void newGame() {
        controller.officeProgressBar.setProgress(1);
        randomTAs(numberOfTAs);
    }

    public void oldGame() throws SQLException {

        ResultSet rs = DataBase.loadData();

        if (rs == null) {
            new Alert(Alert.AlertType.ERROR, "last game data con not be found.\nyou can play a new game");
            newGame();
            return;
        }

        while (rs.next()) {

            ProfessorOffice.health = rs.getInt("officeHealth");
            controller.officeProgressBar.setProgress(ProfessorOffice.health / 500f);

            double x = rs.getDouble("x");
            double y = rs.getDouble("y");
//            int power = rs.getInt("power");
//            int speed = rs.getInt("speed");
            int health = rs.getInt("health");

            Hero h = null;

            if (rs.getString("type").contains("HeadTA")) {
                h = new HeadTA(x, y);
                allTAs.add(h);
            }
            if (rs.getString("type").contains("TiredTA")) {
                h = new TiredTA(x, y);
                allTAs.add(h);
            }
            if (rs.getString("type").contains("RobotTA")) {
                h = new RobotTA(x, y);
                allTAs.add(h);
            }
            if (rs.getString("type").contains("NormalStudent")) {
                h = new NormalStudent(x, y);
                allStudents.add(h);
            }
            if (rs.getString("type").contains("SmartStudent")) {
                h = new SmartStudent(x, y);
                allStudents.add(h);
            }
            if (rs.getString("type").contains("FastStudent")) {
                h = new FastStudent(x, y);
                allStudents.add(h);
            }
            assert h != null;
            h.setHealth(health);
            h.setTranslateX(x);
            h.setTranslateY(y);
            controller.bg.getChildren().add(h);
        }
    }

    public void randomTAs(int number) {

        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(4), e -> {
            int line = (int) (Math.random() * 5 + 1);

            if (line == 1)
                createTA(startPoint, line1);
            if (line == 2)
                createTA(startPoint, line2);
            if (line == 3)
                createTA(startPoint, line3);
            if (line == 4)
                createTA(startPoint, line4);
            if (line == 5)
                createTA(startPoint, line5);

        }));
        timer.setCycleCount(number);
        timer.play();
    }

    public void createTA(int x, int y) {

        Hero h = null;

        int rand = (int) (Math.random() * 3 + 1);

        if (rand == 1)
            h = new TiredTA(x, y);
        if (rand == 2)
            h = new HeadTA(x, y);
        if (rand == 3)
            h = new RobotTA(x, y);

        bg.getChildren().add(h);
        allTAs.add(h);
    }

    private ProgressBar createProgressBar(Hero hero) {
        ProgressBar pb = new ProgressBar();
        pb.setTranslateX(hero.getTranslateX());
        pb.setTranslateY(hero.getTranslateY() - 10);
        pb.setMaxWidth(50);
        pb.setMaxHeight(25);
        pb.setStyle("-fx-accent: #ff8763 ;");
        Platform.runLater(() -> bg.getChildren().add(2, pb));
        return pb;
    }

    private ProgressIndicator createProgressIndicator(Hero hero) {
        ProgressIndicator pi = new ProgressIndicator();
        pi.setTranslateX(hero.getTranslateX() - 10);
        pi.setTranslateY(hero.getTranslateY());
        pi.setStyle("-fx-accent: #ff8763;");
        Platform.runLater(() -> bg.getChildren().add(2, pi));
        return pi;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateGame() {

        ArrayList<Hero> all = new ArrayList<>();
        all.addAll(allStudents);
        all.addAll(allTAs);

        for (Hero h1 : all) {

            // detect collision and attack
            if (!h1.isAttacking) {

                all.stream().filter(h1::isEnemy).forEach(enemy -> {

                    if (h1.detectCollision(enemy)) {

                        h1.isAttacking = true;

                        new Thread(() -> {

                            float totalHealth = 100;
                            ProgressBar heroHealth = createProgressBar(h1);

                            while (h1.isAlive() && enemy.isAlive()) {

                                Platform.runLater(() -> heroHealth.setProgress(h1.getHealth() / totalHealth));
                                h1.attack(enemy);
                                sleep(600);

                            }

                            h1.isAttacking = false;

                            Platform.runLater(() -> {

                                bg.getChildren().remove(heroHealth);

                                if (!h1.isAlive()) {
                                    // score
                                    if (allTAs.contains(h1)) {
                                        score++;
                                        controller.lbl_score.setText("" + score);
                                    }

                                    // update number of TAs
                                    numberOfTAs--;

                                    bg.getChildren().remove(h1);
                                    all.remove(h1);
                                    allStudents.remove(h1);
                                    allTAs.remove(h1);
                                }
                            });

                        }).start();
                    }
                });

            }

            // attack office
            if (!h1.isAttacking && h1.getTranslateX() <= 120 && allTAs.contains(h1)) {

                h1.isAttacking = true;

                float totalHealth = 500;

                new Thread(() -> {

                    while (ProfessorOffice.health > 0 && h1.getHealth() > 0) {

                        Platform.runLater(() -> controller.officeProgressBar.setProgress(ProfessorOffice.health / totalHealth));

                        Platform.runLater(() -> {
                            if (h1 instanceof HeadTA)
                                ((HeadTA) h1).attackOffice();
                            if (h1 instanceof TiredTA)
                                ((TiredTA) h1).attackOffice();
                            if (h1 instanceof RobotTA)
                                ((RobotTA) h1).attackOffice();

                            sleep(500);

                        });

                        if (ProfessorOffice.health <= 0) {
                            try {
                                new Win().start(new Stage());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }

                    }

                }).start();

            }

            // walk
            if (!h1.isAttacking)
                h1.walk();
        }
    }

    public void dragHeroes() {

        SmartStudent s1 = new SmartStudent(240, 7);
        NormalStudent s2 = new NormalStudent(360, 7);
        FastStudent s3 = new FastStudent(480, 7);
        NormalStudent s4 = new NormalStudent(600, 7);

        studentHeroesGroup = new Group(s1, s2, s3, s4);
        bg.getChildren().add(studentHeroesGroup);

        ProgressIndicator pi1 = createProgressIndicator(s1);
        ProgressIndicator pi2 = createProgressIndicator(s2);
        ProgressIndicator pi3 = createProgressIndicator(s3);
        ProgressIndicator pi4 = createProgressIndicator(s4);

        pi1.setProgress(0.5);
        pi2.setProgress(0.5);
        pi3.setProgress(0.5);
        pi4.setProgress(0.5);

        progressIndicators.add(pi1);
        progressIndicators.add(pi2);
        progressIndicators.add(pi3);
        progressIndicators.add(pi4);

        updateProgressIndicators();

        for (Node h : studentHeroesGroup.getChildren()) {

            AtomicReference<Hero> copy = new AtomicReference<>();

            h.setOnMousePressed(e -> {
                copy.set(((Hero) h).getCopy());
                bg.getChildren().add(copy.get());
            });

            h.setOnMouseDragged(e -> {
                copy.get().setTranslateX(e.getSceneX());
                copy.get().setTranslateY(e.getSceneY());

                double y = copy.get().getTranslateY();

                if (y < 100) {

                    controller.line1.setVisible(true);
                    controller.line2.setVisible(false);
                    controller.line3.setVisible(false);
                    controller.line4.setVisible(false);
                    controller.line5.setVisible(false);

                } else if (y >= 100 && y < 200) {

                    controller.line1.setVisible(false);
                    controller.line2.setVisible(true);
                    controller.line3.setVisible(false);
                    controller.line4.setVisible(false);
                    controller.line5.setVisible(false);

                } else if (y >= 200 && y < 300) {

                    controller.line1.setVisible(false);
                    controller.line2.setVisible(false);
                    controller.line3.setVisible(true);
                    controller.line4.setVisible(false);
                    controller.line5.setVisible(false);

                } else if (y >= 300 && y < 400) {

                    controller.line1.setVisible(false);
                    controller.line2.setVisible(false);
                    controller.line3.setVisible(false);
                    controller.line4.setVisible(true);
                    controller.line5.setVisible(false);

                } else if (y >= 400) {

                    controller.line1.setVisible(false);
                    controller.line2.setVisible(false);
                    controller.line3.setVisible(false);
                    controller.line4.setVisible(false);
                    controller.line5.setVisible(true);

                }
            });

            h.setOnMouseReleased(e -> {

                // update progress indicator
                int i = studentHeroesGroup.getChildren().indexOf(h);
                progressIndicators.get(i).setProgress(0);
                timelines.get(i).play();

                // remove red color from lines
                controller.line1.setVisible(false);
                controller.line2.setVisible(false);
                controller.line3.setVisible(false);
                controller.line4.setVisible(false);
                controller.line5.setVisible(false);

                allStudents.add(copy.get());
                copy.get().setTranslateX(150);

                double y = copy.get().getTranslateY();
                if (y < 100)
                    copy.get().setTranslateY(line1);
                if (y >= 100 && y < 200)
                    copy.get().setTranslateY(line2);
                if (y >= 200 && y < 300)
                    copy.get().setTranslateY(line3);
                if (y >= 300 && y < 400)
                    copy.get().setTranslateY(line4);
                if (y >= 400)
                    copy.get().setTranslateY(line5);

//                notify();
//                copy.get().walk();
            });
        }
    }

    public void updateProgressIndicators() {

        Timeline t = new Timeline();
        Timeline finalT = t;

        for (int i = 0; i < progressIndicators.size(); i++) {

            int finalI = i;

            t = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

                if (progressIndicators.get(finalI).getProgress() < 1)
                    progressIndicators.get(finalI).setProgress(progressIndicators.get(finalI).getProgress() + 0.03f);

                if (progressIndicators.get(finalI).getProgress() < 1)
                    studentHeroesGroup.getChildren().get(finalI).setDisable(true);

                if (progressIndicators.get(finalI).getProgress() >= 1) {
                    studentHeroesGroup.getChildren().get(finalI).setDisable(false);
                    finalT.pause();
                }

            }));

            timelines.add(t);
            t.setCycleCount(Animation.INDEFINITE);
            t.play();
        }
    }

    public void endGameTAs() {

        AtomicReference<Hero> h = new AtomicReference<>();

//        finalRandomTAs(line1);
//        finalRandomTAs(line2);
//        finalRandomTAs(line3);
//        finalRandomTAs(line4);
//        finalRandomTAs(line5);

        Hero bigHero = new HeadTA(startPoint, line3);
        bigHero.setFitWidth(300);
        bigHero.setFitHeight(300);

        bg.getChildren().add(bigHero);
        allTAs.add(bigHero);
    }

    private void finalRandomTAs(int line) {

        AtomicReference<Hero> h = new AtomicReference<>();

        Platform.runLater(() -> {

            int rand = (int) (Math.random() * 4 + 1);

            for (int i = 0; i < rand; i++) {

                int rand2 = (int) (Math.random() * 3 + 1);
                if (rand2 == 1)
                    h.set(new RobotTA(startPoint, line));
                if (rand2 == 2)
                    h.set(new HeadTA(startPoint, line));
                if (rand2 == 3)
                    h.set(new TiredTA(startPoint, line));

                bg.getChildren().add(h.get());
                allTAs.add(h.get());
                sleep(1000);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}