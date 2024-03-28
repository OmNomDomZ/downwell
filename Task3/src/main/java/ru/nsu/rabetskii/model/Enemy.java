package ru.nsu.rabetskii.model;

public class Enemy extends MyObject{
    private boolean enemyOnGround = true;
    private GameObject ground;
    public Enemy(int x, int y, GameObject ground){
        this.x = x;
        this.y = y;
        hp = 1;
        width = 25;
        height = 25;
        speed = 5;
        this.ground = ground;
    }

    public void updateGameState() {
        if (x + speed >= ground.getWidth() - ground.getX() || x + speed <= ground.getX()){
            enemyOnGround = false;
        }
        if(!enemyOnGround) {
            speed *= -1;
            enemyOnGround = true;
        }
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
