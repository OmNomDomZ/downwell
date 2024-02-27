package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public abstract class Command {
    public abstract void runCommand(ExecutionContext context, List<String> arguments);
}
