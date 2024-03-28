package ru.nsu.rabetskii.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements AutoCloseable{
    private List<GameObject> bullets;
    private GameObject player;
    private GameObject ground;
    private List<GameObject> enemies;
    private ModelListener listener;
    private Thread ticker;
    public Model(){
        ground = new Ground(10, 400, 500, 50);
        enemies = new ArrayList<>();
        GameObject enemy = new Enemy(new Point(10, 375), ground);
        enemies.add(enemy);
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
        checkCollision();
        updateEnemyGameState();
        updateBulletGameState();

    }

    private void checkCollision() {
        checkPlayerGroundCollision();
        checkPlayerEnemyCollision();
        checkBulletEnemyCollision();
        checkBulletGroundCollision();
    }

    private void checkPlayerGroundCollision() {
        if (ground.collidesWith(player)) {
            int playerY = ground.getPoint().y - player.getHeight();
            player.setPoint(new Point(player.getPoint().x, playerY + 1));
            player.setOnGround(true);
        } else {
            player.setOnGround(false);
        }
    }


    private void checkPlayerEnemyCollision(){
        for (GameObject enemy : enemies){
            if (player.collidesWith(enemy)){
                player.getDamage();
            }
        }
    }

    private void checkBulletEnemyCollision(){
        Iterator<GameObject> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            GameObject bullet = bulletIterator.next();
            for (GameObject enemy : enemies) {
                if (bullet instanceof MachineGun && bullet.collidesWith(enemy)) {
                    enemies.remove(enemy);
                    bulletIterator.remove();
                    break;
                }
            }
        }
    }

    private void checkBulletGroundCollision(){

    }

    private void updateBulletGameState(){
        for (GameObject bullet : bullets){
            bullet.updateGameState();
        }
    }

    private void updateEnemyGameState(){
        for (GameObject enemy : enemies){
            enemy.updateGameState();
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

    public List<GameObject> getEnemies() {
        return enemies;
    }

    public void setListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void close() throws InterruptedException {
        ticker.interrupt();
//        if (ticker.isAlive()) {
            ticker.join();
//        }
    }
}
