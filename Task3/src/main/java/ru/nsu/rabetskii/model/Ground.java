package ru.nsu.rabetskii.model;

public class Ground extends MyObject {
    public Ground(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void updateGameState() {

    }

    @Override
    public void getDamage() {

    }

    @Override
    public void setOnGround(boolean status) {

    }

    @Override
    public boolean getOnGround() {
        return false;
    }
}
