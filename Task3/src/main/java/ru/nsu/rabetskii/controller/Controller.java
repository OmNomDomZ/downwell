package ru.nsu.rabetskii.controller;

import ru.nsu.rabetskii.model.Model;
import ru.nsu.rabetskii.model.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private final Model model;
    public Controller(Model model){
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getPlayer() instanceof Player player){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    player.setMovingLeft(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setMovingRight(true);
                    break;
                case KeyEvent.VK_SPACE:
                    player.setKeySpacePressed(true);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (model.getPlayer() instanceof Player player){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    player.setMovingLeft(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setMovingRight(false);
                    break;
                case KeyEvent.VK_SPACE:
                    player.setKeySpacePressed(false);
                    break;
            }
        }
    }
}
