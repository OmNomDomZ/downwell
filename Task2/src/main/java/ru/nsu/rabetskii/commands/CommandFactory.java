package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public abstract class CommandFactory {
    public abstract void defineCommand();
    public abstract void runCommand(ExecutionContext context, String[] str);
}
