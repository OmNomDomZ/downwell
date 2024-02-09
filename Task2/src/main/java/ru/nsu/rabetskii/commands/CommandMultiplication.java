package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public class CommandMultiplication extends CommandFactory {
    public void defineCommand() {
        System.out.println("Multiplication");
    }
    public void runCommand(ExecutionContext context, String[] str){
        Double var1 = context.Pop();
        Double var2 = context.Pop();
        context.Push(var1 * var2);
    }
}