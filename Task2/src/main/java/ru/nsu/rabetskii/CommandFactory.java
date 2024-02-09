package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommandFactory {
    public Command createCommand(String commandName) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        String Command = properties.getProperty(commandName);
        Class<?> CommandClass = Class.forName(Command);
        Command command = (Command) CommandClass.newInstance();

        return command;
    }

}
