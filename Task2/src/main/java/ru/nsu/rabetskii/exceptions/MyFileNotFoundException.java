package ru.nsu.rabetskii.exceptions;

public class MyFileNotFoundException extends RuntimeException{
    public MyFileNotFoundException(String fileName){
        super("Файл с именем: " + fileName + "не найден");
    }
}
