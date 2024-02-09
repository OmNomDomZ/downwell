package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandPop extends CommandFactory {
    public void defineCommand() {
        System.out.println("Pop");
    }
    public void runCommand(ExecutionContext context, String[] str){
        context.Pop();
    }
}
