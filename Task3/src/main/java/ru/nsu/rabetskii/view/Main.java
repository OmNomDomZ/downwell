   package ru.nsu.rabetskii.view;

import ru.nsu.rabetskii.model.Model;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        SwingUtilities.invokeLater(() -> {
            View mainWindow = new View(model);
            mainWindow.setVisible(true);
        });
    }
}
