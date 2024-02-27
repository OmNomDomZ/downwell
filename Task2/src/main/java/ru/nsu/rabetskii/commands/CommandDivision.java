package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class CommandDivision extends Command {
    public void runCommand(ExecutionContext context, List<String> arguments){
        Double var1 = context.pop();
        Double var2 = context.pop();
        context.push(var1 / var2);
    }
}