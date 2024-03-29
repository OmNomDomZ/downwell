package ru.nsu.rabetskii.model;

import java.util.List;

public class Player extends MyObject{
    private final double GRAVITY = 0.5;
    private final double FALL_SPEED = 0.0;
    private final int JUMP_HEIGHT = 50;
    private double fallSpeed;
    private boolean keyLeftPressed;
    private boolean keyRightPressed;
    private boolean keySpacePressed;
    private List<GameObject> bullets;
    private final int maxNumBullets;
    private int currentNumBullets;

    public Player(List<GameObject> bullets){
        x = 10;
        y = 10;
        width = 30;
        height = 30;
        hp = 4;
        speed = 10;
        fallSpeed = FALL_SPEED;
        objectOnGround = false;
        this.bullets = bullets;
        maxNumBullets = 6;
        currentNumBullets = maxNumBullets;
    }

    public void shoot(){
        if (!objectOnGround && currentNumBullets != 0) {
            MachineGun bullet = new MachineGun(x + width / 2, y, 10);
            bullets.add(bullet);
            currentNumBullets--;
        }
    }


    public void updateGameState() {
        if (keyLeftPressed){
            x -= speed;
        } else if (keyRightPressed){
            x += speed;
        }
        if (keySpacePressed){
            if (objectOnGround){
                y -= JUMP_HEIGHT;
            } else
                if (currentNumBullets != 0){
                shoot();
            }
        }

        if (objectOnGround){
            fallSpeed = 0;
            currentNumBullets = maxNumBullets;
        } else{
            y += (int) fallSpeed;
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
            x = 10;
            y = 10;
        }
    }

    public void setKeyLeftPressed(boolean keyLeftPressed) {
        this.keyLeftPressed = keyLeftPressed;
    }

    public void setKeyRightPressed(boolean keyRightPressed) {
        this.keyRightPressed = keyRightPressed;
    }

    public void setKeySpacePressed(boolean keySpacePressed) {
        this.keySpacePressed = keySpacePressed;
    }
}