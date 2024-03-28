package ru.nsu.rabetskii.model;

import java.awt.*;

public class MachineGun extends MyObject{
    public MachineGun(Point point, int speed){
        this.point = point;
        this.speed = speed;
        this.width = 5;
        this.height = 5;
        hp = 100;
    }

    public void updateGameState(){
        point.y += speed;
        hp--;
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
