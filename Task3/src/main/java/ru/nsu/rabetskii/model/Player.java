package ru.nsu.rabetskii.model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Player extends MyObject{
    private final double GRAVITY = 0.5;
    private final double FALL_SPEED = 0.0;
    private final int JUMP_HEIGHT = 300;
    private double fallSpeed;
    private boolean keyLeftPressed;
    private boolean keyRightPressed;
    private boolean keySpacePressed;
    private boolean playerOnGround;
    private List<GameObject> bullets;
    private boolean gameOver;
    private boolean jumpAbility;
    private boolean shootAbility;
    private int maxNumBullets;

    public Player(List<GameObject> bullets){
        point = new Point(10, 10);
        width = 25;
        height = 25;
        hp = 4;
        speed = 10;
        fallSpeed = FALL_SPEED;
        playerOnGround = false;
        this.bullets = bullets;
        gameOver = false;
        jumpAbility = false;
        shootAbility = true;
        maxNumBullets = 3;
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
        if (bullets.size() < maxNumBullets){
            MachineGun bullet = new MachineGun(new Point(point.x + width / 2, point.y), 10);
            bullets.add(bullet);
        } else if(bullets.size() == maxNumBullets && shootAbility){
            bullets.removeFirst();
            MachineGun bullet = new MachineGun(new Point(point.x + width / 2, point.y), 10);
            bullets.add(bullet);
        } else {
            shootAbility = false;
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
            } else {
                shoot();
                keySpacePressed = false;
            }
        }

        if (playerOnGround){
            fallSpeed = 0;
            shootAbility = true;
            jumpAbility = true;
        } else{
            point = new Point((int) point.getX(), (int) (point.getY() + fallSpeed));
            fallSpeed = fallSpeed < 10 ? fallSpeed + GRAVITY : fallSpeed;
        }
    }

    @Override
    public void getDamage() {
        hp--;
        if (hp == 0){
            gameOver = true;
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