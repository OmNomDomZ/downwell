package ru.nsu.rabetskii;

import java.util.Stack;

abstract class CommandFactory {
    public abstract void defineCommand();
    public abstract void runCommand(Stack<Integer> stack);
}
