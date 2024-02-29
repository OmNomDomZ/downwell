package ru.nsu.rabetskii;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 0) {
            ReadFile reader = new ReadFile();
            for (String name : args){
                reader.fileReader(name);
            }
        }
        else{
            CommandLineInput commandLineInput = new CommandLineInput();
            commandLineInput.getInput();
        }
    }
}