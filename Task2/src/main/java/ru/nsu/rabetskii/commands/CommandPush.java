package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandPush extends CommandFactory {
    public void defineCommand() {
        System.out.println("Plus");
    }
    public void runCommand(ExecutionContext context, String[] str){
        if (str[1].chars().allMatch( Character::isDigit)){
            context.Push(Double.parseDouble(str[1]));
        }
        else {
            context.Push(context.GetDefinedValue(str[1]));
        }
    }
}