package ru.nsu.rabetskii.view;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetImage {
    private Properties properties;
    public ImageIcon get(String fileName){
        properties = new Properties();
        try(InputStream input = getClass().getResourceAsStream("/images.properties")){
            properties.load(input);
            String imagePath = properties.getProperty(fileName);

            ImageIcon image = new ImageIcon(imagePath);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
