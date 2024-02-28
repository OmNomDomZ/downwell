package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.Command;
import ru.nsu.rabetskii.exceptions.*;

import java.util.List;

public class Calculator {
    private final CommandFactory factory = new CommandFactory();

    public void calculate(ExecutionContext context, List<String> arguments) {
        Command curCommand;
        try {
            curCommand = factory.createCommand(arguments.getFirst());
        } catch (UnknownCommandException | CommandCreationException | FileNotFoundException e){
            System.out.println("Произошла ошибка! " + e.getMessage());
            return;
        }

        try {
            curCommand.runCommand(context, arguments);
        } catch (IncorrectNumOfArgsException | IncorrectVarNameException | MyEmptyStackException |
                    NonExisteVariableException | StrIsNotDoubleException | InsufficientArgumentsException e){
            System.out.println("Произошла ошибка! " + e.getMessage());
        }
    }
}
