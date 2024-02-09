package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandSQRT extends Command {
    public void runCommand(ExecutionContext context, String[] str){
        Double var = context.Pop();
        context.Push(Math.sqrt(var));
    }
}