package ru.nsu.rabetskii.exceptions;

public class NonExisteVariableException extends RuntimeException{
    public NonExisteVariableException(String command){
        super("Обращение к несуществующей переменной во время вызова команды: " + command);
    }

}
