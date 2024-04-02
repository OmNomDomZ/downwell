package ru.nsu.rabetskii.model.GameObject;

public class Laser extends MyObject{

    public Laser(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 5;
        this.height = 300;
        hp = 2;
    }
    @Override
    public void updateGameState() {
        hp--;
    }

    @Override
    public void getDamage() {

    }
}
