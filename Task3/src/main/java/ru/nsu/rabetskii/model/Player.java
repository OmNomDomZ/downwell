package ru.nsu.rabetskii.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Player extends MyObject{
    private final double GRAVITY = 0.5;
    private final double FALL_SPEED = 0.0;
    private final int JUMP_HEIGHT = 50;
    private double fallSpeed;
    private boolean keyLeftPressed;
    private boolean keyRightPressed;
    private boolean keySpacePressed;
    private boolean playerOnGround;
    private List<GameObject> bullets;
    private final int maxNumBullets;
    private int currentNumBullets;

    public Player(List<GameObject> bullets){
        point = new Point(10, 10);
        width = 30;
        height = 30;
        hp = 4;
        speed = 10;
        fallSpeed = FALL_SPEED;
        playerOnGround = false;
        this.bullets = bullets;
        maxNumBullets = 6;
        currentNumBullets = maxNumBullets;
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
        if (!playerOnGround && currentNumBullets != 0) {
            MachineGun bullet = new MachineGun(new Point(point.x + width / 2, point.y), 10);
            bullets.add(bullet);
            currentNumBullets--;
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
            point = new Point( point.x - speed, point.y);
        } else if (keyRightPressed){
            point = new Point( point.x + speed, point.y);
        }
        if (keySpacePressed){
            if (playerOnGround){
                point = new Point(point.x, point.y - JUMP_HEIGHT);
            } else
                if (currentNumBullets != 0){
                shoot();
                keySpacePressed = false;
            }
        }

        if (playerOnGround){
            fallSpeed = 0;
            currentNumBullets = maxNumBullets;
        } else{
            point = new Point((int) point.getX(), (int) (point.getY() + fallSpeed));
            fallSpeed = fallSpeed < 10 ? fallSpeed + GRAVITY : fallSpeed;
        }

        // удаляем пулю из списка
        bullets.removeIf(bullet -> bullet.getHp() == 0);
    }

    @Override
    public void getDamage() {
        hp--;
        if (hp == 0){

        } else {
            point = new Point(10, 10);
        }
    }

    public void setOnGround(boolean status) {
        this.playerOnGround = status;
    }

    public  boolean getOnGround(){
        return playerOnGround;
    }
}