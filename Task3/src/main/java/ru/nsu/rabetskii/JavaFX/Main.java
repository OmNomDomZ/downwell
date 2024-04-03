package ru.nsu.rabetskii.JavaFX;

import ru.nsu.rabetskii.model.Model;

public class Main {
    public static void main(String args[]){
        Model model = new Model("MACHINE_GUN");
        new GameView(model);
    }
}
