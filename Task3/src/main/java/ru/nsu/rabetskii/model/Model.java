package ru.nsu.rabetskii.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model implements AutoCloseable{
    private List<GameObject> bullets;
    private GameObject player;
    private GameObject ground;
    private GameObject enemy;
    private ModelListener listener;
    private Thread ticker;
    public Model(){
        ground = new Ground(10, 400, 500, 50);
        enemy = new Enemy(new Point(10, 375), ground);
        bullets = new ArrayList<>();
        player = new Player(bullets);
        ticker = new Ticker(this);
        ticker.start();
        generate();
    }

    public void generate() {
        updateGameState();
        notifyListener();
    }

    private void notifyListener() {
        if (listener != null) {
            listener.onModelChanged();
        }
    }

    private void updateGameState(){
        player.setOnGround(ground.collidesWith(player));
        player.updateGameState();
        enemy.updateGameState();
        updateBulletGameState();

    }

    private void updateBulletGameState(){
        for (GameObject bullet : bullets){
            bullet.updateGameState();
        }
    }

    public GameObject getPlayer() {
        return player;
    }

    public List<GameObject> getBullet() {
        return bullets;
    }

    public GameObject getGround() {
        return ground;
    }

    public GameObject getEnemy() {
        return enemy;
    }

    public void setListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void close() throws InterruptedException {
        ticker.interrupt();
        ticker.join();
    }
}
