package ru.nsu.rabetskii.model;

public class Enemy extends MyObject{
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        hp = 1;
        width = 25;
        height = 25;
        speed = 5;
    }

    public void updateGameState() {
        x += speed;
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
