package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.Command;

import java.util.List;

public class Calculator {
    private final CommandFactory factory = new CommandFactory();

    public void calculate(ExecutionContext context, List<String> arguments) throws RuntimeException {
        Command curCommand;
        curCommand = factory.createCommand(arguments.getFirst());

        curCommand.runCommand(context, arguments);
    }
}
