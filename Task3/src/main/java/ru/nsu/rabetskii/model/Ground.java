package ru.nsu.rabetskii.model;

import java.awt.*;

public class Ground extends MyObject {
    public Ground(int x, int y, int width, int height){
        this.point = new Point(x, y);
        this.width = width;
        this.height = height;
    }
}
