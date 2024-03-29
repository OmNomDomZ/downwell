package ru.nsu.rabetskii.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements AutoCloseable{
    private GameObject player;
    private List<GameObject> bullets;
    private List<GameObject> land;
    private List<GameObject> enemies;
    private ModelListener listener;
    private Thread ticker;
    public Model(){
        land = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        player = new Player(bullets);

        loadLevel("/level.txt");

        ticker = new Ticker(this);
        ticker.start();
        generate();
    }

    private void loadLevel(String filename){
        try (InputStream inputStream = getClass().getResourceAsStream(filename);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] args = line.split(" ");

                String objectType = args[0];
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);

                switch (objectType){
                    case "GROUND":
                        land.add(new Ground(x, y, Integer.parseInt(args[3]), Integer.parseInt(args[4])));
                        break;
                    case "ENEMY":
                        enemies.add(new Enemy(x, y));
                        break;
                    }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        checkEnemyGroundCollision();
    }

    private void checkEnemyGroundCollision(){
        Iterator<GameObject> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            GameObject enemy = enemyIterator.next();
            for (GameObject ground : land) {
                if (enemy.getX() + enemy.getSpeed() >= ground.getWidth() - ground.getX() ||
                        enemy.getX() + enemy.getSpeed() <= ground.getX()) {
                    enemy.setSpeed(enemy.getSpeed() * -1);
                    enemy.setOnGround(true);
                    break;
                }
            }
        }
    }

    private void checkPlayerGroundCollision() {
        for (GameObject ground : land){
            if (ground.collidesWith(player)) {
                int playerY = ground.getY() - player.getHeight();
                player.setY(playerY + 1);
                player.setOnGround(true);
                break;
            } else {
                player.setOnGround(false);
            }
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

    public List<GameObject> getGround() {
        return land;
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
        ticker.join();
    }
}
