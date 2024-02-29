package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public interface Command {
    void runCommand(ExecutionContext context, List<String> args);
}
