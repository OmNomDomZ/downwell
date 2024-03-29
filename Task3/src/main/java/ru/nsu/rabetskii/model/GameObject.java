package ru.nsu.rabetskii.model;

public interface GameObject {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    void updateGameState();
    void getDamage();
    boolean collidesWith(GameObject object);
    int getWidth();
    int getHeight();
    void setOnGround(boolean status);
    boolean getOnGround();
    int getSpeed();
    void setSpeed(int speed);
    int getHp();
}
