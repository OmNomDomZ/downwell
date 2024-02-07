package ru.nsu.rabetskii;

import java.util.Stack;

class CommandPlus extends CommandFactory {
    public void defineCommand() {
        System.out.println("Plus");
    }
    public void runCommand(Stack<Integer> stack){
        Integer first = stack.pop();
        Integer second = stack.pop();
        stack.push(first + second);
    }
}
