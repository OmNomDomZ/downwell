package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandPrint extends Command {
    public void runCommand(ExecutionContext context, String[] str){
        System.out.println(context.Print());
    }
}