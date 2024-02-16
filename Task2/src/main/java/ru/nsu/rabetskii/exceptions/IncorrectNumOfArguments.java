package ru.nsu.rabetskii.exceptions;

public class IncorrectNumOfArguments extends RuntimeException{
    public IncorrectNumOfArguments(String command){
        System.out.println("Неправильное количество аргументов в команде: " + command)  ;
    }
}
