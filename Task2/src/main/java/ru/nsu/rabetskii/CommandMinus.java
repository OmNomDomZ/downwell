package ru.nsu.rabetskii;

import java.util.Stack;

class CommandMinus extends CommandFactory {
    public void defineCommand() {
        System.out.println("Minus");
    }
    public void runCommand(Stack<Integer> stack){
        Integer first = stack.pop();
        Integer second = stack.pop();
        stack.push(first - second);
    }
}
