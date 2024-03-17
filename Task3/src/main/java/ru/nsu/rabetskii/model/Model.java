package ru.nsu.rabetskii.model;

import javax.swing.Timer;

public class Model implements AutoCloseable{
    private Player player = new Player();
    private Ground ground = new Ground(10, 400, 500, 50);
    private Timer timer;
    private ModelListener listener;
    public Model(){
        timer = new Timer(20, e -> {
            this.updateGameState();
            notifyListener();
        });
        timer.start();
    }

    private void notifyListener() {
        if (listener != null) {
            listener.onModelChanged();
        }
    }

    private void updateGameState(){
        player.setPlayerOnGround(ground.collidesWith(player));
        player.updatePlayerGameState();
    }

    public Player getPlayer() {
        return player;
    }

    public Ground getGround() {
        return ground;
    }

    public void setListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void close() {
        timer.stop();
    }
}
