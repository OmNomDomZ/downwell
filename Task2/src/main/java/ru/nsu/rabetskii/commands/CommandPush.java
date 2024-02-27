package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class CommandPush extends Command {
    public void runCommand(ExecutionContext context, List<String> arguments){
        if (arguments.get(1).chars().allMatch( Character::isDigit)){
            context.push(Double.parseDouble(arguments.get(1)));
        }
        else {
            context.push(context.getDefinedValue(arguments.get(1)));
        }
    }
}