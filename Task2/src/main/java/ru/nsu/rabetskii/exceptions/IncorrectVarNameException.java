package ru.nsu.rabetskii.exceptions;

public class IncorrectVarNameException extends RuntimeException{
    public IncorrectVarNameException(String command){
        super("Неверное имя переменной в команде: " + command);
    }
}
