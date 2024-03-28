package ru.nsu.rabetskii.model;

import java.awt.Point;

public class Enemy extends MyObject{
    private boolean enemyOnGround = true;
    private GameObject ground;
    public Enemy(Point point, GameObject ground){
        this.point = point;
        hp = 1;
        width = 25;
        height = 25;
        speed = 5;
        this.ground = ground;
    }

    public void updateGameState() {
        if (this.point.x + speed >= ground.getWidth() - ground.getPoint().x || this.point.x + speed <= ground.getPoint().x){
            enemyOnGround = false;
        }
        if(!enemyOnGround) {
            speed *= -1;
            enemyOnGround = true;
        }
        point = new Point(point.x + speed, point.y);
    }

    @Override
    public void getDamage() {
        hp--;
    }

    @Override
    public void setOnGround(boolean status) {

    }

    @Override
    public boolean getOnGround() {
        return false;
    }
}
