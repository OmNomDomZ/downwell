package ru.nsu.rabetskii.swing.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame extends JFrame implements ActionListener {
    JLabel label;
    JButton playAgainButton;
    JButton exitButton;

    ResultFrame(String labelText, Color labelColor){

        label = new JLabel();
        label.setText(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 50));
        label.setForeground(labelColor);
        label.setBackground(new Color(0x766f6f));
        label.setOpaque(true);

        playAgainButton = new JButton("Play again");
        playAgainButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        playAgainButton.addActionListener(this);
        playAgainButton.setFocusable(false);
        playAgainButton.setBackground(new Color(0x766f6f));
        playAgainButton.setForeground(new Color(0x590808));

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
        panel.add(playAgainButton, gbc);

        gbc.gridy = 2;
        panel.add(exitButton, gbc);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAgainButton){
            SwingUtilities.invokeLater(() -> {
                new StartMenu();
                dispose();
            });
        } else if (e.getSource() == exitButton){
            dispose();
            System.exit(0);
        }
    }
}
