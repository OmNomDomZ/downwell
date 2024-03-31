package ru.nsu.rabetskii.model;

public abstract class MyObject implements GameObject {
    protected int hp;
    protected int speed;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean objectOnGround;
    protected boolean movingLeft;
    protected boolean movingRight;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public void setOnPlatform(boolean status) {
        this.objectOnGround = status;
    }

    public  boolean getOnPlatform(){
        return objectOnGround;
    }

    public int getHp(){
        return hp;
    }

    public boolean getMovingLeft() {
        return movingLeft;
    }
    public boolean getMovingRight() {
        return movingRight;
    }
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean collidesWith(GameObject object) {
        int x1 = this.getX();
        int y1 = this.getY();
        int width1 = this.getWidth();
        int height1 = this.getHeight();

        int x2 = object.getX();
        int y2 = object.getY();
        int width2 = object.getWidth();
        int height2 = object.getHeight();

        return x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;
    }
}
