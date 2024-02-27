package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;
import ru.nsu.rabetskii.exceptions.IncorrectNumOfArguments;

import java.util.List;

public class CommandDefine extends Command {
    public void runCommand(ExecutionContext context, List<String> arguments){
        if (arguments.size() == 3){
            context.define(arguments.get(1), Double.parseDouble(arguments.get(2)));
        }
        else{
            throw new IncorrectNumOfArguments(arguments.getFirst());
        }
    }
}
