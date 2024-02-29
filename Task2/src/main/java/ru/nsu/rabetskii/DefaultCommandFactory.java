package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.*;
import ru.nsu.rabetskii.exceptions.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DefaultCommandFactory implements CommandFactory {
    private Command command;
    private Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    public Command createCommand(String commandName) throws CommandCreationException {
        try {
            loadProperties();
            instantiateCommand(commandName);
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new CommandCreationException("Ошибка при создании экземпляра команды");
        }

        return command;
    }

    private void loadProperties() {
        try (FileReader fileReader = new FileReader(CONFIG_FILE)) {
            properties.load(fileReader);
        } catch (IOException e){
            throw new FileNotFoundException(CONFIG_FILE);
        }
    }

    private void instantiateCommand(String commandName) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (properties.containsKey(commandName)) {
            String curCommand = properties.getProperty(commandName);
            Class<?> commandClass = Class.forName(curCommand);
            command = (Command) commandClass.getDeclaredConstructor().newInstance();
        } else {
            throw new UnknownCommandException(commandName);
        }
    }
}
