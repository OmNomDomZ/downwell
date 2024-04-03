package ru.nsu.rabetskii.JavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.nsu.rabetskii.model.Model;
import ru.nsu.rabetskii.model.ModelListener;
import ru.nsu.rabetskii.model.gameobject.GameObject;
import ru.nsu.rabetskii.model.gameobject.Player;

import java.awt.*;
import java.util.List;

public class GameView extends Application implements ModelListener {
    private Model model;
    private Pane mainPane;
    private Label playerLabel;
    private ProgressBar bulletBar;
    private Label scoreLabel;
    private boolean gameOver;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public GameView(Model model) {
        this.model = model;
        mainPane = new Pane();
        playerLabel = createPlayerLabel();
        bulletBar = createBulletBar();
        scoreLabel = createScoreLabel();
        mainPane.getChildren().addAll(playerLabel, bulletBar, scoreLabel);

        Scene scene = new Scene(mainPane, screenSize.getWidth(), screenSize.getHeight());
        scene.setFill(Color.DARKGRAY);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        model.setListener(this);
    }

    private Label createPlayerLabel() {
        Label label = new Label();
        Image playerImage = new Image(getClass().getResourceAsStream("/defaultPlayer.png"));
        ImageView playerImageView = new ImageView(playerImage);
        label.setGraphic(playerImageView);
        label.setLayoutX(model.getPlayer().getX());
        label.setLayoutY(model.getPlayer().getY());
        label.setPrefWidth(model.getPlayer().getWidth());
        label.setPrefHeight(model.getPlayer().getHeight());
        return label;
    }

    private ProgressBar createBulletBar() {
        ProgressBar bar = new ProgressBar();
        bar.setLayoutX(1200);
        bar.setLayoutY(300);
        bar.setPrefHeight(300);
        return bar;
    }

    private Label createScoreLabel() {
        Label label = new Label();
        label.setTextFill(Color.LIGHTGRAY);
        label.setLayoutX(100);
        label.setLayoutY(110);
        label.setPrefWidth(200);
        label.setPrefHeight(50);
        return label;
    }

    private void createPlatformLabel() {
        Image platformImage = new Image(getClass().getResourceAsStream("/platform.png"));
        for (GameObject platform : model.getPlatforms()) {
            for (int i = 0; i < platform.getWidth(); i += platformImage.getWidth()) {
                ImageView platformImageView = new ImageView(platformImage);
                platformImageView.setLayoutX(platform.getX() + i);
                platformImageView.setLayoutY(platform.getY());
                platformImageView.setFitWidth(platformImage.getWidth());
                platformImageView.setFitHeight(platform.getHeight());
                mainPane.getChildren().add(platformImageView);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

    @Override
    public void onModelChanged() {
        playerLabel.setLayoutX(model.getPlayer().getX());
        playerLabel.setLayoutY(model.getPlayer().getY());

        // Update bullet bar
        if (model.getPlayer() instanceof Player player) {
            int maxNumBullets = player.getMaxNumBullets();
//            bulletBar.setMax(maxNumBullets);
            int curNumBullets = player.getCurrentNumBullets();
            bulletBar.setProgress(curNumBullets / (double) maxNumBullets);
        }

        scoreLabel.setText("Killed: " + model.getScore());

        // Remove old platforms
        mainPane.getChildren().removeIf(node -> node.getId() != null && node.getId().equals("platform"));

        // Create new platforms
        createPlatformLabel();

        // Handle game over or victory
        if (model.getVictory() && !gameOver) {
            // Handle victory
            gameOver = true;
            // Show victory message
        } else if (model.getGameOver() && !gameOver) {
            // Handle game over
            gameOver = true;
            // Show game over message
        }
    }
}
