package ru.nsu.rabetskii.javafx.controller;

import javafx.scene.input.KeyEvent;

import ru.nsu.rabetskii.model.Model;
import ru.nsu.rabetskii.model.gameobject.Player;

public class Controller {
    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void handleKeyPress(KeyEvent event) {
        if (model.getPlayer() instanceof Player player) {
            switch (event.getCode()) {
                case LEFT:
                    player.setMovingLeft(true);
                    break;
                case RIGHT:
                    player.setMovingRight(true);
                    break;
                case SPACE:
                    player.setKeySpacePressed(true);
                    break;
            }
        }
    }

    public void handleKeyRelease(KeyEvent event) {
        if (model.getPlayer() instanceof Player player) {
            switch (event.getCode()) {
                case LEFT:
                    player.setMovingLeft(false);
                    break;
                case RIGHT:
                    player.setMovingRight(false);
                    break;
                case SPACE:
                    player.setKeySpacePressed(false);
                    break;
            }
        }
    }
}
