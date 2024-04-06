package ru.nsu.rabetskii.model.gameobject;

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
    void setWidth(int width);
    void setHeight(int height);
    void setOnPlatform(boolean status);
    boolean getOnPlatform();
    int getSpeed();
    void setSpeed(int speed);
    int getHp();
    boolean getMovingLeft();
    boolean getMovingRight();
    void setMovingLeft(boolean movingLeft);
    void setMovingRight(boolean movingRight);
}
