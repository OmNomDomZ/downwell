package ru.nsu.rabetskii;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 0) {
            ReadFile reader = new ReadFile();
            for (String name : args){
                try{
                    reader.fileReader(name);
                } catch(FileNotFoundException e){
                    System.out.println("Произошла ошибка! " + e.getMessage());
                }
            }
        }
        else{
            CommandLineInput commandLineInput = new CommandLineInput();
            commandLineInput.getInput();
        }
    }
}