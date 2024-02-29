package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.Command;

import java.util.List;

public class Calculator {

    public void calculate(ExecutionContext context, List<String> arguments, CommandFactory factory) {
        Command curCommand;
        curCommand = factory.createCommand(arguments.getFirst());
        curCommand.runCommand(context, arguments);
    }
}
