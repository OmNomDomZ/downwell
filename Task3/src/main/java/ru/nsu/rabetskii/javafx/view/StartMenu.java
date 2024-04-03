package ru.nsu.rabetskii.javafx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import ru.nsu.rabetskii.model.Model;

public class StartMenu extends Stage {
    private Button startButton;
    private Button machineGunButton;
    private Button laserButton;
    private Button exitButton;
    private String weapon;
    public StartMenu(){
        Label label = new Label("DownWell");
        label.setFont(Font.font("SansSerif", FontWeight.BOLD, 50));
        label.setTextFill(Color.rgb(90, 13, 13));
        label.setStyle("-fx-background-color: #766f6f;");
        label.setPadding(new Insets(10));

        startButton = new Button("Play");
        machineGunButton = new Button("Machine Gun");
        laserButton = new Button("Laser");
        exitButton = new Button("EXIT");

        startButton.setOnAction(event -> handleButtonClick("start"));
        machineGunButton.setOnAction(event -> handleButtonClick("machineGun"));
        laserButton.setOnAction(event -> handleButtonClick("laser"));
        exitButton.setOnAction(event -> close());

        startButton.setPrefSize(250, 50);
        machineGunButton.setPrefSize(250, 50);
        laserButton.setPrefSize(250, 50);
        exitButton.setPrefSize(250, 50);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25));
        gridPane.setStyle("-fx-background-color: #766f6f;");

        gridPane.add(label, 0, 0);
        gridPane.add(startButton, 0, 1);
        gridPane.add(machineGunButton, 0, 2);
        gridPane.add(laserButton, 0, 3);
        gridPane.add(exitButton, 0, 4);

        Scene scene = new Scene(gridPane, 500, 500);
        setScene(scene);
        setTitle("Start Menu");
        show();
    }

    private void handleButtonClick(String buttonType) {
        if (buttonType.equals("start")) {
            if (weapon == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a weapon.", ButtonType.OK).showAndWait();
            } else {
                Model model = new Model(weapon);
                GameView gameView = new GameView(model);
                gameView.show();
                close();
            }
        } else if (buttonType.equals("machineGun")) {
            weapon = "MACHINE_GUN";
            machineGunButton.setStyle("-fx-background-color: #6076d2;");
            laserButton.setStyle("-fx-background-color: #ffff;");
        } else if (buttonType.equals("laser")) {
            weapon = "LASER";
            laserButton.setStyle("-fx-background-color: #6076d2;");
            machineGunButton.setStyle("-fx-background-color: #ffff;");
        }
    }
}