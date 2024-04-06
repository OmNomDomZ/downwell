package ru.nsu.rabetskii.javafx.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import ru.nsu.rabetskii.javafx.controller.Controller;
import ru.nsu.rabetskii.model.Model;
import ru.nsu.rabetskii.model.ModelListener;
import ru.nsu.rabetskii.model.gameobject.GameObject;
import ru.nsu.rabetskii.model.gameobject.Player;

public class GameView extends Stage implements ModelListener {
    private final Model model;
    private final Controller controller;
    private final Pane mainPane;
    private final Scene scene;
    private ImageView playerImageView;
    private ImageView finishImageView;
    private Label hpLabel;
    private Label scoreLabel;
    private ProgressBar bulletBar;
    private final int verticalSpeed = 7;
    private boolean gameOver = false;
    private final double screenWidth = Screen.getPrimary().getBounds().getWidth();
    private final double screenHeight = Screen.getPrimary().getBounds().getHeight();


    public GameView(Model model) {
        this.model = model;
        this.controller = new Controller(model);

        mainPane = new Pane();
        mainPane.setStyle("-fx-background-color: #333333;");
        playerImageView = createPlayerImageView();
        hpLabel = createHpLabel();
        bulletBar = createBulletBar();
        scoreLabel = createScoreLabel();
        createPlatformImageViews();
        createWallImageViews();

        setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        scene = new Scene(mainPane, screenWidth, screenHeight);
        scene.setFill(Color.rgb(51, 51, 51));
        setScene(scene);

        mainPane.getChildren().add(hpLabel);
        mainPane.getChildren().add(playerImageView);
        mainPane.getChildren().add(bulletBar);
        mainPane.getChildren().add(scoreLabel);
        scene.setOnKeyPressed(event -> controller.handleKeyPress(event));
        scene.setOnKeyReleased(event -> controller.handleKeyRelease(event));

        model.setListener(this);
    }

    private ImageView createPlayerImageView() {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/defaultPlayer.png")));
        imageView.setFitWidth(model.getPlayer().getWidth());
        imageView.setFitHeight(model.getPlayer().getHeight());
        imageView.setLayoutX(model.getPlayer().getX());
        imageView.setLayoutY(model.getPlayer().getY());
        return imageView;
    }

    private Label createHpLabel(){
        Label hpLabel = new Label();
        hpLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        hpLabel.setTextFill(Color.LIGHTGRAY);
        hpLabel.setLayoutX(100);
        hpLabel.setLayoutY(50);
        hpLabel.setPrefSize(250, 50);
        hpLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hpLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        hpLabel.setStyle("-fx-background-color: #940909;");
        hpLabel.setAlignment(Pos.CENTER);
        hpLabel.setContentDisplay(ContentDisplay.CENTER);
        return hpLabel;
    }

    private ProgressBar createBulletBar(){
        ProgressBar bar = new ProgressBar();
        bar.setStyle("-fx-control-inner-background: darkgray; -fx-accent: #940909;");
        bar.getTransforms().add(new Rotate(270));
        bar.setLayoutX(1200);
        bar.setLayoutY(600);
        bar.setPrefSize(300, 40);
        return bar;
    }

    private Label createScoreLabel(){
        Label scoreLabel = new Label();
        scoreLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 24));
        scoreLabel.setTextFill(Color.LIGHTGRAY);
        scoreLabel.setLayoutX(100);
        scoreLabel.setLayoutY(110);
        scoreLabel.setPrefSize(200, 50);
        scoreLabel.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        scoreLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scoreLabel.setStyle("-fx-background-color: #000000;");
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setContentDisplay(ContentDisplay.CENTER);
        return scoreLabel;
    }

    private void createPlatformImageViews() {
        Image image = new Image(getClass().getResourceAsStream("/platform.png"));
        for (GameObject platform : model.getPlatforms()) {
            for (int i = 0; i < platform.getWidth(); i += image.getWidth()){
                ImageView platformImageView = new ImageView(image);
                platformImageView.setFitWidth(image.getWidth());
                platformImageView.setFitHeight(image.getHeight());
                platformImageView.setLayoutX(platform.getX() + i);
                platformImageView.setLayoutY(platform.getY());
                mainPane.getChildren().add(platformImageView);
            }
        }
    }

    private void createWallImageViews() {
        Image leftWallImage = new Image(getClass().getResourceAsStream("/leftWall.png"));
        Image rightWallImage = new Image(getClass().getResourceAsStream("/rightWall.png"));
        for (GameObject wall : model.getWalls()) {
            Image wallImage = wall.getX() < screenWidth / 2 ? leftWallImage : rightWallImage;
            for (int i = 0; i < wall.getHeight(); i += wallImage.getHeight()){
                ImageView wallImageView = new ImageView(wallImage);
                wallImageView.setFitWidth(wallImage.getWidth());
                wallImageView.setFitHeight(wallImage.getHeight());
                wallImageView.setLayoutX(wall.getX());
                wallImageView.setLayoutY(wall.getY() + i);
                mainPane.getChildren().add(wallImageView);
            }
        }
    }

    private void createFinishImageView() {
        if (finishImageView == null) {
            finishImageView = new ImageView(new Image(getClass().getResourceAsStream("/finish.png")));
            finishImageView.setFitWidth(model.getFinish().getWidth());
            finishImageView.setFitHeight(model.getFinish().getHeight());
            finishImageView.setLayoutX(model.getFinish().getX());
            finishImageView.setLayoutY(model.getFinish().getY());
            mainPane.getChildren().add(finishImageView);
        }
    }

    @Override
    public void onModelChanged() {
        Platform.runLater(() -> {
            playerImageView.setLayoutX(model.getPlayer().getX());
            playerImageView.setLayoutY(model.getPlayer().getY());

            if (!model.getPlayer().getOnPlatform()) {
                double maxY = screenHeight * 3 - screenHeight;
                if (model.getPlayer().getY() > screenHeight / 2 - mainPane.getLayoutY() &&
                        mainPane.getLayoutY() > -maxY) {
                    mainPane.setLayoutY(mainPane.getLayoutY() - verticalSpeed);
                    hpLabel.setLayoutY(hpLabel.getLayoutY() + verticalSpeed);
                    bulletBar.setLayoutY(bulletBar.getLayoutY() + verticalSpeed);
                    scoreLabel.setLayoutY(scoreLabel.getLayoutY() + verticalSpeed);
                }
            }

            if (model.getPlayer() instanceof Player player){
                int maxNumBullets = player.getMaxNumBullets();
                int curNumBullets = player.getCurrentNumBullets();
                bulletBar.setProgress((double)curNumBullets / maxNumBullets);
            }
            scoreLabel.setText("Killed: " + model.getScore());
            hpLabel.setText(model.getPlayer().getHp() + " / 4");

            //удаление
            mainPane.getChildren().removeIf(node -> ("bullet").equals(node.getId()) ||
                    ("enemy").equals(node.getId()) || ("breakablePlatform").equals(node.getId()) ||
                    ("bat").equals(node.getId()));

            for (GameObject bullet : model.getBullet()){
                if ((bullet.getY() > -mainPane.getLayoutY()) &&
                        (bullet.getY() < -mainPane.getLayoutY() + screenHeight)){
                    Label bulletLabel = new Label();
                    bulletLabel.setId("bullet");
                    bulletLabel.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    bulletLabel.setLayoutX(bullet.getX());
                    bulletLabel.setLayoutY(bullet.getY());
                    bulletLabel.setPrefSize(bullet.getWidth(), bullet.getHeight());
                    mainPane.getChildren().add(bulletLabel);
                }
            }

            for (GameObject defaultEnemy : model.getDefaultEnemies()){
                if ((defaultEnemy.getY() > -mainPane.getLayoutY()) &&
                        (defaultEnemy.getY() < -mainPane.getLayoutY() + screenHeight)) {
                    ImageView enemy = new ImageView(new Image(getClass().getResourceAsStream("/enemy.png")));
                    enemy.setId("enemy");
                    enemy.setLayoutX(defaultEnemy.getX());
                    enemy.setLayoutY(defaultEnemy.getY());
                    mainPane.getChildren().add(enemy);
                }
            }

            for (GameObject batEnemy : model.getBatEnemies()){
                if (batEnemy.getY() > -mainPane.getLayoutY() &&
                        batEnemy.getY() < -mainPane.getLayoutY() + screenHeight){
                    ImageView enemy = new ImageView(new Image(getClass().getResourceAsStream("/bat.png")));
                    enemy.setId("bat");
                    enemy.setLayoutX(batEnemy.getX());
                    enemy.setLayoutY(batEnemy.getY());
                    mainPane.getChildren().add(enemy);
                }
            }

            for (GameObject breakablePlatform : model.getBreakablePlatform()){
                if (breakablePlatform.getY() > -mainPane.getLayoutY() &&
                    breakablePlatform.getY() < -mainPane.getLayoutY() + screenHeight){
                    ImageView platform = new ImageView(new Image(getClass().getResourceAsStream("/breakablePlatform.png")));
                    platform.setId("breakablePlatform");
                    platform.setLayoutX(breakablePlatform.getX());
                    platform.setLayoutY(breakablePlatform.getY());
                    mainPane.getChildren().add(platform);
                }
            }

            createFinishImageView();
        });

        if (model.getVictory() && !gameOver){
            gameOver = true;
            Platform.runLater(() -> {
                ResultView view = new ResultView("!YOU WIN!", Color.rgb(42, 183, 19));
                view.show();
                close();
            });
        } else if (model.getGameOver() && !gameOver){
            gameOver = true;
            Platform.runLater(() -> {
                ResultView view = new ResultView("YOU LOSE", Color.rgb(122, 16, 16));
                view.show();
                close();
            });
        }
    }
}


