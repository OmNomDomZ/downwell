package ru.nsu.rabetskii.model.gameobject;

import ru.nsu.rabetskii.model.Model;

import java.util.Iterator;

public class Laser extends MyObject{
    private Model model;
    public Laser(int x, int y, int height, Model model){
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = height;
        hp = 2;
        speed = 10;
        this.model = model;
    }
    @Override
    public void updateGameState() {
        hp--;

        Iterator<GameObject> enemyIterator = model.getDefaultEnemies().iterator();
        while (enemyIterator.hasNext()) {
            GameObject enemy = enemyIterator.next();
            if (this.collidesWith(enemy)) {
                enemyIterator.remove();
                break;
            }
        }

        Iterator<GameObject> batEnemyIterator = model.getBatEnemies().iterator();
        while (batEnemyIterator.hasNext()) {
            GameObject batEnemy = batEnemyIterator.next();
            if (this.collidesWith(batEnemy)) {
                batEnemyIterator.remove();
                break;
            }
        }

        Iterator<GameObject> breakablePlatformIterator = model.getBreakablePlatform().iterator();
        while (breakablePlatformIterator.hasNext()) {
            GameObject breakablePlatform = breakablePlatformIterator.next();
            if (this.collidesWith(breakablePlatform)) {
                breakablePlatformIterator.remove();
                break;
            }
        }
    }

    @Override
    public void getDamage() {

    }
}
