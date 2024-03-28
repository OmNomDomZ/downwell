package ru.nsu.rabetskii.model;

public class MachineGun extends MyObject{
    public MachineGun(int x, int y, int speed){
        this.x = x;
        this.y =y;
        this.speed = speed;
        this.width = 5;
        this.height = 5;
        hp = 100;
    }

    public void updateGameState(){
        y += speed;
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
