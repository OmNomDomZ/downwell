package ru.nsu.rabetskii.model;

import ru.nsu.rabetskii.model.GameObject.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements AutoCloseable{
    private final GameObject player;
    private final List<GameObject> bullets;
    private final List<GameObject> enemies;
    private final List<GameObject> platforms;
    private final List<GameObject> walls;
    private final List<GameObject> breakablePlatform;
    private final GameObject finish;
    private ModelListener listener;
    private final Thread ticker;
    private boolean gameOver;
    private boolean victory;
    private int score;
    private final String weapon;
    private enum GameObjectType {
        BREAKABLE_PLATFORM,
        PLATFORM,
        WALL,
        ENEMY
    }
    private enum Weapon {
        MACHINE_GUN,
        LASER,
    }
    public Model(String weapon){
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        player = new Player(bullets, weapon);
        walls = new ArrayList<>();
        breakablePlatform = new ArrayList<>();
        finish = new Finish();

        loadLevel("/level.txt");

        score = 0;
        gameOver = false;
        this.weapon = weapon;

        ticker = new Ticker(this);
        ticker.start();
    }

    private void loadLevel(String filename){
        try (InputStream inputStream = getClass().getResourceAsStream(filename);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] args = line.split(" ");

                GameObjectType objectType = GameObjectType.valueOf(args[0]);
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                int width = 0;
                int height = 0;
                if (args.length == 5){
                    width = Integer.parseInt(args[3]);
                    height = Integer.parseInt(args[4]);
                }

                switch (objectType){
                    case BREAKABLE_PLATFORM:
                        breakablePlatform.add(new BreakablePlatform(x, y));
                        break;
                    case PLATFORM:
                        platforms.add(new Platform(x, y, width, height));
                        break;
                    case WALL:
                        walls.add(new Wall(x, y, width, height));
                        break;
                    case ENEMY:
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
        player.setOnPlatform(false);
        checkPlayerPlatformCollision();
        checkPlayerBreakablePlatformCollision();
        checkPlayerEnemyCollision();
        checkBulletEnemyCollision();
        checkBulletPlatformCollision();
        checkBulletBreakablePlatformCollision();
        checkEnemyPlatformCollision();
        checkPlayerWallCollision();
        checkPlayerFinishCollision();
    }

    private void checkEnemyPlatformCollision(){
        for (GameObject enemy : enemies) {
            boolean onPlatform = false;
            boolean crashed = false;
            for (GameObject platform : platforms) {
                if (enemy.collidesWith(platform) &&
                        enemy.getX() + enemy.getSpeed() > platform.getX() &&
                        enemy.getX() + enemy.getSpeed() + enemy.getWidth() < platform.getX() + platform.getWidth() &&
                        enemy.getY() + enemy.getHeight() >= platform.getY()) {
                    onPlatform = true;
                    for (GameObject otherPlatform : platforms) {
                        if (platform != otherPlatform &&
                                enemy.collidesWith(otherPlatform) &&
                                enemy.getX() + enemy.getSpeed() < otherPlatform.getX() + otherPlatform.getWidth() &&
                                enemy.getX() + enemy.getWidth() + enemy.getSpeed() > otherPlatform.getX()){
                            crashed = true;
                            break;
                        }
                    }
                    break;
                }
            }

            if (!onPlatform || crashed) {
                enemy.setSpeed(enemy.getSpeed() * -1);
            }
        }
    }

    private void checkPlayerFinishCollision(){
        if (player.collidesWith(finish) && !victory){
            victory = true;
        }
    }

    private void checkPlayerWallCollision(){
        for (GameObject wall : walls){
            if (player.collidesWith(wall)) {
                if (player.getMovingLeft() && player.getX() < wall.getX() + wall.getWidth()) {
                    player.setX(wall.getX() + wall.getWidth());
                    player.setMovingLeft(false);
                }
                if (player.getMovingRight() && player.getX() + player.getWidth() > wall.getX()) {
                    player.setX(wall.getX() - player.getWidth());
                    player.setMovingRight(false);
                }
            }
        }
    }

    private void checkPlayerPlatformCollision() {
        checkPlayerGroundCollision(platforms);
    }

    private void checkPlayerBreakablePlatformCollision() {
        checkPlayerGroundCollision(breakablePlatform);
    }

    private void checkPlayerGroundCollision(List<GameObject> ground) {
        for (GameObject platform : ground){
            if (platform.collidesWith(player)) {
                int playerY = platform.getY() - player.getHeight();
                player.setY(playerY + 1);
                player.setOnPlatform(true);
                break;
            }
        }
    }

    private void checkPlayerEnemyCollision(){
        for (GameObject enemy : enemies){
            if (player.collidesWith(enemy)){
                player.getDamage();
                if (player.getHp() == 0){
                    gameOver = true;
                }
                break;
            }
        }
    }

    private void checkBulletEnemyCollision(){
        Iterator<GameObject> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            GameObject bullet = bulletIterator.next();
            for (GameObject enemy : enemies) {
                if (bullet.collidesWith(enemy)) {
                    switch (Weapon.valueOf(weapon)){
                        case MACHINE_GUN:
                            enemies.remove(enemy);
                            score += 1;
                            bulletIterator.remove();
                            break;
                        case LASER:
                            enemies.remove(enemy);
                            score += 1;
                            break;
                    }
                    break;
                }
            }
        }
    }

    private void checkBulletPlatformCollision(){
        for (GameObject platform : platforms){
            for (GameObject bullet : bullets) {
                if (bullet.collidesWith(platform)){
                    switch (Weapon.valueOf(weapon)){
                        case MACHINE_GUN:
                            bullets.remove(bullet);
                            break;
                        case LASER:
                            break;
                    }
                    break;
                }
            }
        }
    }

    private void checkBulletBreakablePlatformCollision(){
        Iterator<GameObject> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            GameObject bullet = bulletIterator.next();
            for (GameObject platform : breakablePlatform) {
                if (bullet.collidesWith(platform)) {
                    switch (Weapon.valueOf(weapon)){
                        case MACHINE_GUN:
                            breakablePlatform.remove(platform);
                            bulletIterator.remove();
                            break;
                        case LASER:
                            breakablePlatform.remove(platform);
                            break;
                    }
                    break;
                }
            }
        }
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

    public int getScore(){
        return score;
    }

    public GameObject getPlayer() {
        return player;
    }
    public GameObject getFinish(){
        return finish;
    }

    public List<GameObject> getBullet() {
        return bullets;
    }

    public List<GameObject> getPlatforms() {
        return platforms;
    }
    public List<GameObject> getBreakablePlatform() {
        return breakablePlatform;
    }
    public List<GameObject> getEnemies() {
        return enemies;
    }
    public List<GameObject> getWalls() {return walls;}
    public boolean getVictory(){
        return victory;
    }
    public boolean getGameOver(){
        return gameOver;
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
