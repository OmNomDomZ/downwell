package ru.nsu.rabetskii.exceptions;

public class IncorrectNumOfArgsException extends RuntimeException{
    public IncorrectNumOfArgsException(String command){
        super("Неправильное количество аргументов в команде: " + command)  ;
    }
}
