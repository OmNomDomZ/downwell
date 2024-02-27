package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.*;
import ru.nsu.rabetskii.exceptions.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommandFactory {
    private Command command;
    private Properties properties = new Properties();

    public Command createCommand(String commandName) throws CommandCreationException {
        try (FileReader fileReader = new FileReader("config.properties")) {
            properties.load(fileReader);
            if (properties.containsKey(commandName)) {
                String curCommand = properties.getProperty(commandName);
                Class<?> commandClass = Class.forName(curCommand);
                command = (Command) commandClass.getDeclaredConstructor().newInstance();
            } else {
                throw new UnknownCommandException(commandName);
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new CommandCreationException("Ошибка при создании команды: ", e);
        } catch (Exception e) {
            throw new CommandCreationException("An unexpected error occurred while creating the command.", e);
        }

        return command;
    }
}
