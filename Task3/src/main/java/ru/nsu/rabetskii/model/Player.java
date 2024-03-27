package ru.nsu.rabetskii.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Player extends MyObject{
    private final double GRAVITY = 0.5;
    private final double FALL_SPEED = 0.0;
    private double fallSpeed;
    private boolean keyLeftPressed;
    private boolean keyRightPressed;
    private boolean keySpacePressed;
    private boolean playerOnGround;
    private List<GameObject> bullets;

    public Player(List<GameObject> bullets){
        point = new Point(10, 10);
        width = 25;
        height = 25;
        hp = 4;
        speed = 10;
        fallSpeed = FALL_SPEED;
        playerOnGround = false;
        this.bullets = bullets;
    }

    public void handleKeyDown(int keyCode){
        if (keyCode == KeyEvent.VK_LEFT){
            keyLeftPressed = true;
        } else if (keyCode == KeyEvent.VK_RIGHT){
            keyRightPressed = true;
        } else if (keyCode == KeyEvent.VK_SPACE){
            keySpacePressed = true;
        }
    }

    public void shoot(){
        if (bullets.size() <= 5){
            MachineGun bullet = new MachineGun(new Point(point.x + width / 2, point.y), 10);
            bullets.add(bullet);
        } else {
            bullets.removeFirst();
            MachineGun bullet = new MachineGun(new Point(point.x + width / 2, point.y), 10);
            bullets.add(bullet);
        }
    }

    public void handleKeyUp(int keyCode){
        if (keyCode == KeyEvent.VK_LEFT){
            keyLeftPressed = false;
        } else if (keyCode == KeyEvent.VK_RIGHT){
            keyRightPressed = false;
        } else if (keyCode == KeyEvent.VK_SPACE){
            keySpacePressed = false;
        }
    }

    public void updateGameState() {
        if (keyLeftPressed){
            point = new Point((int) point.getX() - speed, (int) point.getY());
        } else if (keyRightPressed){
            point = new Point((int) point.getX() + speed, (int) point.getY());
        }
        if (keySpacePressed){
            shoot();
        }

        if (playerOnGround){
            fallSpeed = 0;
        } else{
            point = new Point((int) point.getX(), (int) (point.getY() + fallSpeed));
            fallSpeed = fallSpeed < 10 ? fallSpeed + GRAVITY : fallSpeed;
        }
    }

    @Override
    public void setOnGround(boolean status) {
        this.playerOnGround = status;
    }

    @Override
    public  boolean getOnGround(){
        return playerOnGround;
    }
}