package ru.nsu.rabetskii.exceptions;

public class IncorrectNumOfArguments extends RuntimeException{
    public IncorrectNumOfArguments(String command){
        super("Неправильное количество аргументов в команде: " + command)  ;
    }
}
