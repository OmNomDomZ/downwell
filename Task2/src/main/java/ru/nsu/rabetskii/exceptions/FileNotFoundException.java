package ru.nsu.rabetskii.exceptions;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException(String fileName){
        super("Файл с именем: " + fileName + "не найден");
    }
}
