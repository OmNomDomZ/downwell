package ru.nsu.rabetskii.exceptions;

public class UnknownCommandException extends RuntimeException{
    public UnknownCommandException(String command){
        super("Неизвестная команда: " + command);
    }
}
