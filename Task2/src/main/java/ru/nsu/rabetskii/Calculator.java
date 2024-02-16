package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.Command;
import ru.nsu.rabetskii.exceptions.UnknownCommandException;

import java.io.IOException;

public class Calculator {
    private final CommandFactory factory = new CommandFactory();
    private Command curCommand;
    public void calculate(ExecutionContext context, String[] WordArray)
            throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            curCommand = factory.createCommand(WordArray[0]);
        } catch (UnknownCommandException e){
            System.out.println("Произошла ошибка! " + e.getMessage());
        }
        curCommand.runCommand(context, WordArray);
    }
}
