package ru.nsu.rabetskii.view;

import ru.nsu.rabetskii.controller.Controller;
import ru.nsu.rabetskii.model.GameObject;
import ru.nsu.rabetskii.model.Model;
import ru.nsu.rabetskii.model.ModelListener;
import ru.nsu.rabetskii.model.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameView extends JFrame implements ModelListener {
    private Model model;
    private Controller controller;
    private final JLabel playerLabel;
    private final JLabel mainLabel;
    private final JLabel hpLabel;
    private final JProgressBar bulletBar;
    private final int mainLabelStartX = 0;
    private int mainLabelStartY = 0;
    private final int verticalSpeed = 4;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public GameView(Model model){
        this.model = model;
        controller = new Controller(model);

        mainLabel = new JLabel();

        playerLabel = createPlayerLabel();
        hpLabel = createHpLabel();
        bulletBar = createBulletBar();
        createGroundLabel();
        createWallLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
        getContentPane().setBackground(Color.darkGray);
        setLayout(null);
        setResizable(false);

        mainLabel.add(playerLabel);
        mainLabel.add(hpLabel);
        mainLabel.add(bulletBar);
        this.addKeyListener(controller);
        this.setVisible(true);
        this.add(mainLabel);

        model.setListener(this);
    }

    private JLabel createPlayerLabel() {
        JLabel label = new JLabel();
        ImageIcon playerIcon = new ImageIcon(getClass().getResource("/defaultPlayer.png"));
        label.setIcon(playerIcon);
        label.setBounds(model.getPlayer().getX(), model.getPlayer().getY(),
                model.getPlayer().getWidth(), model.getPlayer().getHeight());
        return label;
    }
    private void createGroundLabel(){
        ImageIcon groundIcon = new ImageIcon(getClass().getResource("/ground.png"));
        for (GameObject ground : model.getGround()){
            for (int i = 0; i < ground.getWidth(); i += groundIcon.getIconWidth()){
                JLabel groundLabel = new JLabel();
                groundLabel.setIcon(groundIcon);
                groundLabel.setBounds(ground.getX() + i, ground.getY(),
                        groundIcon.getIconWidth(), ground.getHeight());
                mainLabel.add(groundLabel);
            }
        }
    }

    private void createWallLabel(){
        ImageIcon leftWallIcon = new ImageIcon(getClass().getResource("/leftWall.png"));
        ImageIcon rightWallIcon = new ImageIcon(getClass().getResource("/rightWall.png"));
        for (GameObject wall : model.getWalls()){
            for (int i = 0; i < wall.getHeight(); i += leftWallIcon.getIconHeight()) {
                JLabel wallLabel = new JLabel();
                if (wall.getX() < screenSize.width / 2){
                    wallLabel.setIcon(leftWallIcon);
                } else {
                    wallLabel.setIcon(rightWallIcon);
                }
                wallLabel.setBounds(wall.getX(), wall.getY() + i,
                        leftWallIcon.getIconWidth(), leftWallIcon.getIconHeight());
                mainLabel.add(wallLabel);
            }
        }
    }

    private JLabel createHpLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.lightGray);
        label.setBounds(100, 50, 250, 50);
        label.setBorder(new LineBorder(Color.lightGray, 4));
        label.setBackground(new Color(0x940909));
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        return label;
    }

    private JProgressBar createBulletBar() {
        JProgressBar bar = new JProgressBar(SwingConstants.VERTICAL);
        bar.setBounds(1200, 300, 40, 300);
        bar.setBackground(Color.darkGray);
        bar.setForeground(new Color(0x940909));
        bar.setValue(0);
        return bar;
    }

    @Override
    public void onModelChanged() {
        SwingUtilities.invokeLater(() -> {
            playerLabel.setBounds(model.getPlayer().getX(), model.getPlayer().getY(),
                    model.getPlayer().getWidth(), model.getPlayer().getHeight());

            // движение mainLabel
            if (!model.getPlayer().getOnGround()) {
                if (model.getPlayer().getY() > (screenSize.height - mainLabelStartY) / 2) {
                    mainLabel.setBounds(mainLabelStartX, mainLabelStartY -= verticalSpeed, screenSize.width, screenSize.height * 3);
                    bulletBar.setLocation(bulletBar.getX(), bulletBar.getY() + verticalSpeed);
                    hpLabel.setLocation(hpLabel.getX(), hpLabel.getY() + verticalSpeed);
                } else {
                    mainLabel.setBounds(mainLabelStartX, mainLabelStartY, screenSize.width, screenSize.height * 3);
                }
            }

            hpLabel.setText(model.getPlayer().getHp() +  " / 4");
            if (model.getPlayer().getHp() == 3){
                hpLabel.setBackground(new Color(0x650606));
            } else if (model.getPlayer().getHp() == 2){
                hpLabel.setBackground(new Color(0x4F0505));
            } else if (model.getPlayer().getHp() == 1) {
                hpLabel.setBackground(new Color(0x3A0404));
            } else if (model.getPlayer().getHp() == 0){
                hpLabel.setBackground(new Color(0x1E0303));
            }

            if(model.getPlayer() instanceof Player player) {
                int maxNumBullets = player.getMaxNumBullets();
                bulletBar.setMaximum(maxNumBullets);
                int curNumBullets = player.getCurrentNumBullets();
                bulletBar.setValue(curNumBullets);
            }


            // Удаление всех старых меток пуль и врагов
            Component[] components = mainLabel.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    String name = component.getName();
                    if (name != null && (name.equals("bullet") || name.equals("enemy"))) {
                        mainLabel.remove(component);
                    }
                }
            }

            // Создание новых меток пуль
            for (GameObject bullet : model.getBullet()) {
                JLabel bulletLabel = new JLabel();
                bulletLabel.setName("bullet"); // Устанавливаем имя метки, чтобы потом можно было их идентифицировать
                bulletLabel.setOpaque(true);
                bulletLabel.setBackground(Color.RED);
                bulletLabel.setBounds(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
                mainLabel.add(bulletLabel);
            }

            // Создание новых меток врагов
            for (GameObject enemy : model.getEnemies()){
                JLabel enemyLabel = new JLabel();
                enemyLabel.setName("enemy");
                ImageIcon enemyIcon = new ImageIcon(getClass().getResource("/enemy.png"));
                enemyLabel.setIcon(enemyIcon);
                enemyLabel.setBounds(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
                mainLabel.add(enemyLabel);
            }
                 repaint();
        });
    }
}
