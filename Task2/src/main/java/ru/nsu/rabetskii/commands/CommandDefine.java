package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandDefine extends CommandFactory {
    public void defineCommand() {
        System.out.println("Define");
    }
    public void runCommand(ExecutionContext context, String[] str){
        context.Define(str[1], Double.parseDouble(str[2]));
    }
}
