package ru.nsu.rabetskii.view;

import ru.nsu.rabetskii.controller.Controller;
import ru.nsu.rabetskii.model.Model;
import ru.nsu.rabetskii.model.ModelListener;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements ModelListener {
    private Model model;
    private Controller controller;
    private GetImage getImage;
    private JLabel playerLabel;
    private JLabel mainLabel;
    private JLabel enemyLabel;
    private int startX = 0;
    private int startY = 0;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public View(Model model){
        this.model = model;
        controller = new Controller(model);
        getImage = new GetImage();

        mainLabel = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
//        setSize(500, 500);
        getContentPane().setBackground(Color.gray);
        setLayout(null);
        setResizable(false);

        playerLabel = new JLabel();
        ImageIcon playerIcon = getImage.get("defaultPlayer");
        playerLabel.setIcon(playerIcon);
        playerLabel.setBounds(model.getPlayer().getPoint().x, model.getPlayer().getPoint().y,
                                model.getPlayer().getWidth(), model.getPlayer().getHeight());


        ImageIcon groundIcon = getImage.get("ground");
        for (int i = 0; i < model.getGround().getWidth(); i += groundIcon.getIconWidth()){
            JLabel tempLabel = new JLabel();
            tempLabel.setIcon(groundIcon);
            tempLabel.setBounds(model.getGround().getPoint().x + i, model.getGround().getPoint().y,
                                    groundIcon.getIconWidth(), model.getGround().getHeight());
            mainLabel.add(tempLabel);
        }

        enemyLabel = new JLabel();
        ImageIcon enemyIcon = getImage.get("enemy");
        enemyLabel.setIcon(enemyIcon);
        enemyLabel.setBounds(model.getEnemy().getPoint().x, model.getEnemy().getPoint().y, 25, 25);

        mainLabel.add(enemyLabel);
        mainLabel.add(playerLabel);
        this.addKeyListener(controller);
        this.setVisible(true);
        this.add(mainLabel);

        model.setListener(this);
    }



    @Override
    public void onModelChanged() {
        SwingUtilities.invokeLater(() -> {
            playerLabel.setBounds(model.getPlayer().getPoint().x, model.getPlayer().getPoint().y,
                    model.getPlayer().getWidth(), model.getPlayer().getHeight());

            if (!model.getPlayer().getOnGround()){
                mainLabel.setBounds(startX, startY--, screenSize.width, screenSize.height);
            } else{
                mainLabel.setBounds(startX, startY, screenSize.width, screenSize.height);
            }

//            this.setBounds(startX, startY--, screenSize.width, screenSize.height);

            // Удаление всех старых меток пуль
            Component[] components = mainLabel.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    String name = component.getName();
                    if (name != null && name.equals("bullet")) {
                        mainLabel.remove(component);
                    }
                }
            }

            // Создание новых меток пуль
            for (int i = 0; i < model.getBullet().size(); ++i) {
                JLabel bulletLabel = new JLabel();
                bulletLabel.setName("bullet"); // Устанавливаем имя метки, чтобы потом можно было их идентифицировать
                bulletLabel.setOpaque(true);
                bulletLabel.setBackground(Color.RED);
                bulletLabel.setBounds(model.getBullet().get(i).getPoint().x, model.getBullet().get(i).getPoint().y,
                    model.getBullet().get(i).getWidth(), model.getBullet().get(i).getHeight());
                mainLabel.add(bulletLabel);
            }

            enemyLabel.setBounds(model.getEnemy().getPoint().x, model.getEnemy().getPoint().y, 25, 25);
            repaint();
        });
    }
}
