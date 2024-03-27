package ru.nsu.rabetskii.model;

import java.awt.*;

public abstract class MyObject implements GameObject {
    protected int hp;
    protected int speed;
    protected Point point;
    protected int width;
    protected int height;


    public Point getPoint() {
        return point;
    }

    public Rectangle getBounds() {
        return new Rectangle(point.x, point.y, width, height);
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getHp(){
        return hp;
    }

    public boolean collidesWith(GameObject object) {
        Rectangle object1Bounds = object.getBounds();
        Rectangle object2Bounds = this.getBounds();
        return object2Bounds.intersects(object1Bounds);
    }
}
