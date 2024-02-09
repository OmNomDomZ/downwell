package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandComment extends CommandFactory {
    public void defineCommand() {
        System.out.println("comment");
    }
    public void runCommand(ExecutionContext context, String[] str){}
}
