package ru.nsu.rabetskii.exceptions;

public class StrIsNotDoubleException extends RuntimeException {
    public StrIsNotDoubleException(String command){
        super("Переданная строка не является числом : " + command);
    }
}
