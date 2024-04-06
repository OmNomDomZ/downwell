package ru.nsu.rabetskii.model.gameobject;

import ru.nsu.rabetskii.model.Model;

import java.awt.*;
import java.util.List;

public class Player extends MyObject{
    private double fallSpeed;
    private boolean keySpacePressed;
    private final List<GameObject> bullets;
    private int maxNumBullets;
    private int currentNumBullets;
    private final String weapon;
    private final Model model;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private enum Weapon {
        MACHINE_GUN,
        LASER,
    }
    public Player(List<GameObject> bullets, String weapon, Model model){
        maxNumBullets = 0;
        x = 770;
        y = 10;
        width = 30;
        height = 30;
        hp = 4;
        speed = 10;
        fallSpeed = 0;
        objectOnGround = false;
        this.bullets = bullets;
        this.weapon = weapon;
        switch (Weapon.valueOf(weapon)){
            case MACHINE_GUN:
                maxNumBullets = 5;
                break;
            case LASER:
                maxNumBullets = 3;
                break;
        }
        currentNumBullets = maxNumBullets;
        this.model = model;
    }

    public void shoot(){
        if (!objectOnGround && currentNumBullets != 0) {
            GameObject bullet = switch (Weapon.valueOf(weapon)) {
                case MACHINE_GUN -> new MachineGun(x + width / 2, y, model);
                case LASER -> new Laser(x + width / 2, y, screenSize.height * 3 - x + width, model);
            };
            bullets.add(bullet);
            currentNumBullets--;
            fallSpeed = 0;
        }
    }

    public int getCurrentNumBullets() {
        return currentNumBullets;
    }

    public int getMaxNumBullets() {
        return maxNumBullets;
    }

    public void updateGameState() {
        if (movingLeft){
            x -= speed;
        } else if (movingRight){
            x += speed;
        }

        if (objectOnGround){
            fallSpeed = 0;
            currentNumBullets = maxNumBullets;
        } else{
            y += (int) fallSpeed;
            double GRAVITY = 0.5;
            double MAX_FALL_SPEED = 9.0;
            fallSpeed = fallSpeed < MAX_FALL_SPEED ? fallSpeed + GRAVITY : fallSpeed;
        }
        // удаляем пулю из списка
        bullets.removeIf(bullet -> bullet.getHp() == 0);

        // playerFinish collision
        if (this.collidesWith(model.getFinish())){
            model.setVictory(true);
        }

        objectOnGround = false;

        // playerWall collision
        for (GameObject wall : model.getWalls()) {
            if (this.collidesWith(wall)) {
                if (getMovingLeft() && x < wall.getX() + wall.getWidth()) {
                    x = wall.getX() + wall.getWidth();
                    setMovingLeft(false);
                } else if (getMovingRight() && x + width > wall.getX()) {
                    x = wall.getX() - width;
                    setMovingRight(false);
                }
            }
        }

        // player defaultPlatform collision
        for (GameObject platform : model.getPlatforms()){
            if (this.collidesWith(platform)){
                 y = platform.getY() - height + 1;
                 objectOnGround = true;
                 break;
            }
        }

        // player breakablePlatform collision
        for (GameObject platform : model.getBreakablePlatform()){
            if (this.collidesWith(platform)){
                y = platform.getY() - height + 1;
                objectOnGround = true;
                break;
            }
        }

        // player defaultEnemy collision
        for (GameObject enemy : model.getDefaultEnemies()){
            if (this.collidesWith(enemy)){
                getDamage();
                if (hp == 0){
                    model.setGameOver(true);
                }
                break;
            }
        }

        // player batEnemy collision
        for (GameObject bat : model.getBatEnemies()){
            double distance = Math.sqrt(Math.pow(x - bat.getX(), 2) + Math.pow(y - bat.getY(), 2));
            if (this.collidesWith(bat)){
                getDamage();
                if (hp == 0){
                    model.setGameOver(true);
                }
                break;
            } else if(distance < 400){
                double deltaX = x - bat.getX();
                double deltaY = y - bat.getY();
                double angle = Math.atan2(deltaY, deltaX);
                double speed = bat.getSpeed();
                bat.setX(bat.getX() + (int)(speed * Math.cos(angle)));
                bat.setY(bat.getY() + (int)(speed * Math.sin(angle)));
            }
        }
    }

    @Override
    public void getDamage() {
        --hp;
        y -= 150;
    }

    public void setKeySpacePressed(boolean keySpacePressed) {
        if (keySpacePressed && !this.keySpacePressed){
            this.keySpacePressed = true;
            if (objectOnGround){
                int JUMP_HEIGHT = 80;
                y -= JUMP_HEIGHT;
            } else
            if (currentNumBullets != 0){
                shoot();
            }
        } else {
            this.keySpacePressed = keySpacePressed;
        }
    }
}