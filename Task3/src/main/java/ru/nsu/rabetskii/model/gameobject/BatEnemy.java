package ru.nsu.rabetskii.model.gameobject;

public class BatEnemy extends MyObject{
    public BatEnemy(int x, int y){
        this.x = x;
        this.y = y;
        width = 20;
        height = 20;
        speed = 4;
    }


    @Override
    public void updateGameState() {

    }

    @Override
    public void getDamage() {

    }
}
