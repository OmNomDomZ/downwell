package ru.nsu.rabetskii.model;

import java.awt.*;

public class Ground extends MyObject {
    public Ground(int x, int y, int width, int height){
        this.point = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void updateGameState() {

    }

    @Override
    public void setOnGround(boolean status) {

    }

    @Override
    public boolean getOnGround() {
        return false;
    }
}
