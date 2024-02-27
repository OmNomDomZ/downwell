package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.Command;
import ru.nsu.rabetskii.exceptions.CommandCreationException;
import ru.nsu.rabetskii.exceptions.IncorrectNumOfArguments;
import ru.nsu.rabetskii.exceptions.UnknownCommandException;

import java.io.IOException;
import java.util.List;

public class Calculator {
    private final CommandFactory factory = new CommandFactory();
    private Command curCommand;
    public void calculate(ExecutionContext context, List<String> arguments) {
        try {
            curCommand = factory.createCommand(arguments.getFirst());
        } catch (UnknownCommandException | CommandCreationException e){
            System.out.println("Произошла ошибка! " + e.getMessage());
            return;
        }

        try {
            curCommand.runCommand(context, arguments);
        } catch (IncorrectNumOfArguments e){
            System.out.println("Произошла ошибка! " + e.getMessage());
        }
    }
}
