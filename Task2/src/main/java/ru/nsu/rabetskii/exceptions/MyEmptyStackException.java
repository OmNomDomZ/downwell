package ru.nsu.rabetskii.exceptions;

public class MyEmptyStackException extends RuntimeException {
    public MyEmptyStackException(String command){
        super("Пустой стек во время вызова комманды: " + command);
    }
}
