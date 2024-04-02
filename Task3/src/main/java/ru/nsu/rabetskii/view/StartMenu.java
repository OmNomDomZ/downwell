package ru.nsu.rabetskii.view;

import ru.nsu.rabetskii.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame implements ActionListener {
    JLabel label;
    JButton startButton;
    JButton MachineGunButton;
    JButton LaserButton;
    String weapon;
    StartMenu(){
        this.weapon = "MACHINE_GUN";

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

        MachineGunButton = new JButton("Machine Gun");
        MachineGunButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        MachineGunButton.addActionListener(this);
        MachineGunButton.setFocusable(false);
        MachineGunButton.setBackground(new Color(0x766f6f));
        MachineGunButton.setForeground(new Color(0x650606));

        LaserButton = new JButton("Laser");
        LaserButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        LaserButton.addActionListener(this);
        LaserButton.setFocusable(false);
        LaserButton.setBackground(new Color(0x766f6f));
        LaserButton.setForeground(new Color(0x590D0D));

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
        panel.add(MachineGunButton, gbc);

        gbc.gridy = 3;
        panel.add(LaserButton, gbc);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == MachineGunButton){
            weapon = "MACHINE_GUN";
            System.out.println(weapon);
        } else if(e.getSource() == LaserButton){
            weapon = "LASER";
            System.out.println(weapon);
        } else if (e.getSource() == startButton){
            Model model = new Model(weapon);
            SwingUtilities.invokeLater(() -> {
                GameView mainWindow = new GameView(model);
                mainWindow.setVisible(true);
            });
        }
    }
}
