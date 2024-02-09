package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

public abstract class Command {
    public abstract void runCommand(ExecutionContext context, String[] str);
}
