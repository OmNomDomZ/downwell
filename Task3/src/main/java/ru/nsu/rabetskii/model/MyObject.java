package ru.nsu.rabetskii.model;

import java.awt.*;

public abstract class MyObject {
    protected final double GRAVITY = 0.5;
    protected final double FALL_SPEED = 0.0;
    protected double fallSpeed;
    protected int startHp;
    protected int speed;
    protected Point point;
    protected int width;
    protected int height;

    public Point getPoint() {
        return point;
    }

    protected Rectangle getBounds() {
        return new Rectangle(point.x, point.y, width, height);
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public boolean collidesWith(MyObject object1) {
        Rectangle object1Bounds = object1.getBounds();
        Rectangle object2Bounds = this.getBounds();
        return object2Bounds.intersects(object1Bounds);
    }
}
