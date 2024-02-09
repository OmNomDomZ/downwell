package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandPop extends Command {
    public void runCommand(ExecutionContext context, String[] str){
        context.Pop();
    }
}
