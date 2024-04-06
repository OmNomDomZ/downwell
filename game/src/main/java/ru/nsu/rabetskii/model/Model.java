package ru.nsu.rabetskii.model;

import ru.nsu.rabetskii.model.gameobject.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Model implements AutoCloseable{
    private final GameObject player;
    private final List<GameObject> bullets;
    private final List<GameObject> defaultEnemies;
    private final List<GameObject> batEnemies;
    private final List<GameObject> platforms;
    private final List<GameObject> walls;
    private final List<GameObject> breakablePlatform;
    private final GameObject finish;
    private ModelListener listener;
    private final Thread ticker;
    private boolean gameOver;
    private boolean victory;
    private final int score;

    private enum GameObjectType {
        BAT_ENEMY,
        BREAKABLE_PLATFORM,
        ENEMY,
        PLATFORM,
        WALL

    }
    public Model(String weapon){
        platforms = new ArrayList<>();
        defaultEnemies = new ArrayList<>();
        batEnemies = new ArrayList<>();
        bullets = new ArrayList<>();
        player = new Player(bullets, weapon, this);
        walls = new ArrayList<>();
        breakablePlatform = new ArrayList<>();
        finish = new Finish();

        loadLevel("/level.txt");

        score = 0;
        gameOver = false;

        ticker = new Ticker(this);
        ticker.start();
    }

    private void loadLevel(String filename){
        try (InputStream inputStream = getClass().getResourceAsStream(filename)) {
            assert inputStream != null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
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
                            defaultEnemies.add(new DefaultEnemy(x, y, this));
                            break;
                        case BAT_ENEMY:
                            batEnemies.add(new BatEnemy(x, y));
                            break;
                        }
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
        updateEnemyGameState();
        updateBulletGameState();
    }

    private void updateBulletGameState(){
        for (GameObject bullet : bullets){
            bullet.updateGameState();
        }
    }

    private void updateEnemyGameState(){
        for (GameObject enemy : defaultEnemies){
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
    public List<GameObject> getDefaultEnemies() {
        return defaultEnemies;
    }
    public List<GameObject> getBatEnemies() {
        return batEnemies;
    }
    public List<GameObject> getWalls() {return walls;}
    public boolean getVictory(){
        return victory;
    }
    public boolean getGameOver(){
        return gameOver;
    }
    public void setVictory(boolean victory) {
        this.victory = victory;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
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
