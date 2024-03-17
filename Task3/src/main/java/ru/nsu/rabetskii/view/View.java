package ru.nsu.rabetskii.view;

import ru.nsu.rabetskii.controller.Controller;
import ru.nsu.rabetskii.model.Model;
import ru.nsu.rabetskii.model.ModelListener;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class View extends JFrame implements ModelListener {
    private Model model;
    private Controller controller;
    private GetImage getImage;
    private JLabel playerLabel;
    private JLabel groundLabel;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public View(Model model){
        this.model = model;
        controller = new Controller(model);
        getImage = new GetImage();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
        setBackground(Color.black);
        getContentPane().setBackground(Color.gray);
        setLayout(null);
        setResizable(false);

        playerLabel = new JLabel();
        ImageIcon playerIcon = getImage.get("defaultPlayer");
        playerLabel.setIcon(playerIcon);
        playerLabel.setBounds(model.getPlayer().getPoint().x, model.getPlayer().getPoint().y,
                                model.getPlayer().getWidth(), model.getPlayer().getHeight());

        groundLabel = new JLabel();
        groundLabel.setBackground(Color.green);
        groundLabel.setOpaque(true);
        groundLabel.setBounds(model.getGround().getPoint().x, model.getGround().getPoint().y,
                model.getGround().getWidth(), model.getGround().getHeight());

        this.add(groundLabel);
        this.add(playerLabel);
        this.addKeyListener(controller);
        this.setVisible(true);

        model.setListener(this);
    }



    @Override
    public void onModelChanged() {
        SwingUtilities.invokeLater(() -> {
            playerLabel.setBounds(model.getPlayer().getPoint().x, model.getPlayer().getPoint().y,
                                    model.getPlayer().getWidth(), model.getPlayer().getHeight());
            repaint();
        });
    }
}
