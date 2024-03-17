package ru.nsu.rabetskii.model;

import java.awt.*;

public class Enemy extends MyObject{
    public Enemy(){
        point = new Point(10, 10);
        startHp = 1;
        speed = 3;
    }
}
