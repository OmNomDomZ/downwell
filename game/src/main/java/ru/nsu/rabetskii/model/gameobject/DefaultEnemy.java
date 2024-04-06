package ru.nsu.rabetskii.model.gameobject;

import ru.nsu.rabetskii.model.Model;

import java.util.Iterator;

public class DefaultEnemy extends MyObject {
    private final Model model;

    public DefaultEnemy(int x, int y, Model model) {
        this.x = x;
        this.y = y;
        hp = 1;
        width = 25;
        height = 25;
        speed = -5;
        this.model = model;
    }

    public void updateGameState() {
        x += speed;


        Iterator<GameObject> platformIterator = model.getPlatforms().iterator();
        boolean onPlatform = false;
        boolean crashed = false;
        while (platformIterator.hasNext()) {
            GameObject platform = platformIterator.next();
            if (this.collidesWith(platform) &&
                    this.getX() + this.getSpeed() > platform.getX() &&
                    this.getX() + this.getSpeed() + this.getWidth() < platform.getX() + platform.getWidth() &&
                    this.getY() + this.getHeight() >= platform.getY()) {
                onPlatform = true;
                for (GameObject otherPlatform : model.getPlatforms()) {
                    if (platform != otherPlatform &&
                            this.collidesWith(otherPlatform) &&
                            this.getX() + this.getSpeed() < otherPlatform.getX() + otherPlatform.getWidth() &&
                            this.getX() + this.getWidth() + this.getSpeed() > otherPlatform.getX()) {
                        crashed = true;
                        break;
                    }
                }
                break;
            }
        }
        if (!onPlatform || crashed) {
            speed *= - 1;
        }
    }


    @Override
    public void getDamage() {
        hp--;
    }

}
