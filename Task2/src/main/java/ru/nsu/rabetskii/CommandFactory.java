package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.*;
import ru.nsu.rabetskii.exceptions.UnknownCommandException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommandFactory {
    private Command command;
    public Command createCommand(String commandName) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        if (properties.containsKey(commandName)){
            String curCommand = properties.getProperty(commandName);
            Class<?> CommandClass = Class.forName(curCommand);
            command = (Command) CommandClass.newInstance();
        }
        else {
            throw new UnknownCommandException(commandName);
        }

        return command;
    }

}
