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

    public void push(double var){
        stack.push(var);
    }

    public double pop(){
        double value = stack.peek();
        stack.pop();
        return value;
    }

    public void define(String name, double var){
        namedParams.put(name, var);
    }

    public double print(){
        return stack.peek();
    }

    public boolean contain(String name){
        return namedParams.containsKey(name);
    }

    public double getDefinedValue(String name){
        return namedParams.get(name);
    }
}

