package ru.nsu.rabetskii.exceptions;

public class InsufficientArgumentsException extends RuntimeException{
    public InsufficientArgumentsException(String command){
        super("Недостаточно аргументов при вызове команды: " + command);
    }
}
