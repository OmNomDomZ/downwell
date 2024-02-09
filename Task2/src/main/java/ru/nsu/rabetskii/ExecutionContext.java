package ru.nsu.rabetskii;

import java.util.HashMap;
import java.util.Stack;

public class ExecutionContext {
    private final Stack<Double> stack;
    private final HashMap<String, Double> namedParams;

    public ExecutionContext() {
        namedParams = new HashMap<>();
        stack = new Stack<>();
    }

    public void Push(Double var){
        stack.push(var);
    }

    public Double Pop(){
        Double value = stack.peek();
        stack.pop();
        return value;
    }

    public void Define(String name, Double var){
        namedParams.put(name, var);
    }

    public void Print(){
        System.out.println(stack.peek());
    }

    public Double GetDefinedValue(String name){
        return namedParams.get(name);
    }
}

