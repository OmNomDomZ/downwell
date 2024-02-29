package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.Command;
import ru.nsu.rabetskii.exceptions.CommandCreationException;

public interface CommandFactory {
    Command createCommand(String commandName) throws CommandCreationException;
}
