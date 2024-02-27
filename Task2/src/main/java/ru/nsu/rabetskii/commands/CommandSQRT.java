package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class CommandSQRT extends Command {
    public void runCommand(ExecutionContext context, List<String> arguments){
        Double var = context.pop();
        context.push(Math.sqrt(var));
    }
}