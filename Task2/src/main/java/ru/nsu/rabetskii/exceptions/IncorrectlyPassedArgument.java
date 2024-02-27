package ru.nsu.rabetskii.exceptions;

public class IncorrectlyPassedArgument extends RuntimeException{
    public IncorrectlyPassedArgument(String command){
        super("Неверно переданный аргумент в команде: " + command);
    }
}
