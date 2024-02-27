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

    public void push(Double var){
        stack.push(var);
    }

    public Double pop(){
        Double value = stack.peek();
        stack.pop();
        return value;
    }

    public void define(String name, Double var){
        namedParams.put(name, var);
    }

    public Double print(){
        return stack.peek();
    }

    public Double getDefinedValue(String name){
        return namedParams.get(name);
    }
}

