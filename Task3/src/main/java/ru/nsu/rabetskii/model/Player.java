package ru.nsu.rabetskii.model;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends MyObject{
    private boolean keyLeftPressed;
    private boolean keyRightPressed;
    private boolean keySpacePressed;
    private boolean playerOnGround;
    private boolean jumpAbility;
    public Player(){
        point = new Point(10, 10);
        width = 25;
        height = 25;
        startHp = 4;
        speed = 10;
        fallSpeed = FALL_SPEED;
        playerOnGround = false;
        jumpAbility = true;
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

    public void handleKeyUp(int keyCode){
        if (keyCode == KeyEvent.VK_LEFT){
            keyLeftPressed = false;
        } else if (keyCode == KeyEvent.VK_RIGHT){
            keyRightPressed = false;
        } else if (keyCode == KeyEvent.VK_SPACE){
            keySpacePressed = false;
        }
    }

    public void setPlayerOnGround(boolean playerOnGround) {
        this.playerOnGround = playerOnGround;
    }

    public void updatePlayerGameState() {
        if (keyLeftPressed){
            point = new Point((int) point.getX() - speed, (int) point.getY());
        } else if (keyRightPressed){
            point = new Point((int) point.getX() + speed, (int) point.getY());
        } else if (keySpacePressed && jumpAbility){
            point = new Point((int) point.getX(), (int) point.getY() - speed);
            jumpAbility = false;
        }

        if (playerOnGround){
            fallSpeed = 0;
            jumpAbility = true;
        } else{
            point = new Point((int) point.getX(), (int) (point.getY() + fallSpeed));
            fallSpeed = fallSpeed < 10 ? fallSpeed + GRAVITY : fallSpeed;
        }
    }
}