package ru.nsu.rabetskii.swing.view;

import ru.nsu.rabetskii.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame implements ActionListener {
    JLabel label;
    JButton startButton;
    JButton machineGunButton;
    JButton laserButton;
    JButton exitButton;
    String weapon;
    StartMenu(){
        label = new JLabel();
        label.setText("DownWell");
        label.setFont(new Font("SansSerif", Font.BOLD, 50));
        label.setForeground(new Color(0x590D0D));
        label.setBackground(new Color(0x766f6f));
        label.setOpaque(true);

        startButton = new JButton("Play");
        startButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        startButton.addActionListener(this);
        startButton.setFocusable(false);
        startButton.setBackground(new Color(0x766f6f));
        startButton.setForeground(new Color(0x0C440C));

        machineGunButton = new JButton("Machine Gun");
        machineGunButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        machineGunButton.addActionListener(this);
        machineGunButton.setFocusable(false);
        machineGunButton.setBackground(new Color(0x766f6f));
        machineGunButton.setForeground(new Color(0x650606));

        laserButton = new JButton("Laser");
        laserButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        laserButton.addActionListener(this);
        laserButton.setFocusable(false);
        laserButton.setBackground(new Color(0x766f6f));
        laserButton.setForeground(new Color(0x590D0D));

        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);
        exitButton.setForeground(new Color(0xA29898));
        exitButton.setBackground(new Color(0x590D0D));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0x766f6f));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        // отступы сверху и снизу между компонентами
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(label, gbc);

        gbc.gridy = 1;
        panel.add(startButton, gbc);

        gbc.gridy = 2;
        panel.add(machineGunButton, gbc);

        gbc.gridy = 3;
        panel.add(laserButton, gbc);

        gbc.gridy = 4;
        panel.add(exitButton, gbc);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == machineGunButton){
            weapon = "MACHINE_GUN";
            machineGunButton.setBackground(new Color(0x252D48));
            laserButton.setBackground(new Color(0x766f6f));
        } else if(e.getSource() == laserButton){
            weapon = "LASER";
            laserButton.setBackground(new Color(0x252D48));
            machineGunButton.setBackground(new Color(0x766f6f));
        } else if (e.getSource() == exitButton) {
            dispose();
            System.exit(0);
        } else if (e.getSource() == startButton){
            if(weapon == null){
                JOptionPane.showMessageDialog(this, "Please select a weapon.", "Error", JOptionPane.ERROR_MESSAGE);
            } else{
                Model model = new Model(weapon);
                SwingUtilities.invokeLater(() -> {
                    GameView mainWindow = new GameView(model);
                    mainWindow.setVisible(true);
                    dispose();
                });
            }
        }
    }
}
