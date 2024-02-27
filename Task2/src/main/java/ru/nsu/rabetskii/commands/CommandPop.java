package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class CommandPop extends Command {
    public void runCommand(ExecutionContext context, List<String> arguments){
        context.pop();
    }
}
