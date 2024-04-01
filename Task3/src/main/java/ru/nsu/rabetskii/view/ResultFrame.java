package ru.nsu.rabetskii.view;

import ru.nsu.rabetskii.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame extends JFrame implements ActionListener {
    JLabel label;
    JButton button;

    ResultFrame(String labelText, Color labelColor){

        label = new JLabel();
        label.setText(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 50));
        label.setForeground(labelColor);
        label.setBackground(new Color(0x766f6f));
        label.setOpaque(true);

        button = new JButton("Play again");
        button.setFont(new Font("SansSerif", Font.BOLD, 30));
        button.addActionListener(this);
        button.setFocusable(false);
        button.setBackground(new Color(0x766f6f));
        button.setForeground(new Color(0x590808));

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
        panel.add(button, gbc);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            Model model = new Model();
            SwingUtilities.invokeLater(() -> {
                GameView mainWindow = new GameView(model);
                mainWindow.setVisible(true);
            });
        }
    }
}
