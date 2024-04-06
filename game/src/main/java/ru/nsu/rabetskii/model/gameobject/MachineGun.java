package ru.nsu.rabetskii.model.gameobject;

import ru.nsu.rabetskii.model.Model;

import java.util.Iterator;

public class MachineGun extends MyObject{
    private Model model;
    public MachineGun(int x, int y, Model model){
        this.x = x;
        this.y = y;
        this.speed = 10;
        this.width = 5;
        this.height = 5;
        hp = 100;
        this.model = model;
    }

    @Override
    public void updateGameState(){
        y += speed;
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
                hp = 0;
                break;
            }
        }

        Iterator<GameObject> platformIterator = model.getPlatforms().iterator();
        while (platformIterator.hasNext()) {
            GameObject platform = platformIterator.next();
            if (this.collidesWith(platform)) {
                hp = 0;
                break;
            }
        }
    }

    @Override
    public void getDamage() {

    }
}
