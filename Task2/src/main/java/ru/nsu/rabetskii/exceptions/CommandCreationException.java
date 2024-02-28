package ru.nsu.rabetskii.exceptions;

public class CommandCreationException extends RuntimeException {
    public CommandCreationException(String message) {
        super(message);
    }
}