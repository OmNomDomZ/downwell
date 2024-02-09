package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandPrint extends CommandFactory {
    public void defineCommand() {
        System.out.println("Print");
    }
    public void runCommand(ExecutionContext context, String[] str){
        context.Print();
    }
}