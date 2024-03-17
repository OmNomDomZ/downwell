package ru.nsu.rabetskii.controller;

import ru.nsu.rabetskii.model.Model;

import java.awt.*;
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
        int keyCode = e.getKeyCode();
        model.getPlayer().handleKeyDown(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        model.getPlayer().handleKeyUp(keyCode);
    }
}
