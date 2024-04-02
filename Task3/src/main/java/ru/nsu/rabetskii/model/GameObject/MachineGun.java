package ru.nsu.rabetskii.model.GameObject;

public class MachineGun extends MyObject{
    public MachineGun(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = 5;
        this.height = 5;
        hp = 100;
    }

    @Override
    public void updateGameState(){
        y += speed;
        hp--;
    }

    @Override
    public void getDamage() {

    }
}
