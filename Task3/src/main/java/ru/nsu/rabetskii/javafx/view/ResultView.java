package ru.nsu.rabetskii.javafx.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ResultView extends Stage {
    public ResultView(String labelText, Color labelColor){
        Label label = new Label();
        label.setFont(Font.font("SansSerif", FontWeight.BOLD, 50));
        label.setText(labelText);
        label.setTextFill(labelColor);

        setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        Button playAgainButton = new Button("Play again");
        playAgainButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
        playAgainButton.setOnAction(event -> {
            Platform.runLater(() -> {
                new StartMenu();
                close();
            });
        });

        Button exitButton = new Button("EXIT");
        exitButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
        exitButton.setOnAction(event -> {
            close();
            System.exit(0);
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setStyle("-fx-background-color: #766f6f;");

        gridPane.add(label, 0, 0);
        gridPane.add(playAgainButton, 0 , 1);
        gridPane.add(exitButton, 0, 2);

        Scene scene = new Scene(gridPane, 500, 500);
        setScene(scene);
        show();
    }
}
