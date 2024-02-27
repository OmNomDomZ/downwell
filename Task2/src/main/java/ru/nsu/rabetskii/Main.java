package ru.nsu.rabetskii;

public class Main {
    public static void main(String[] args) {
        if (args.length != 0) {
            ReadFile reader = new ReadFile();
            for (String name : args){
                reader.FileReader(name);
            }
        }
        else{
            CommandLineInput commandLineInput = new CommandLineInput();
            commandLineInput.getInput();
        }
    }
}