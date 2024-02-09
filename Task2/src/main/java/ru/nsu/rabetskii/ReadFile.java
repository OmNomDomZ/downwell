package ru.nsu.rabetskii;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;

import ru.nsu.rabetskii.commands.*;

public class ReadFile {
    private final CommandFactory factory = new CommandFactory();
    private final ExecutionContext context = new ExecutionContext();
    public void FileReader(String fileName) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new FileReader(fileName))){
            while(scanner.hasNext()){

                String line = scanner.nextLine();
                String[] WordArray = line.split(" ");

                Command curCommand = factory.createCommand(WordArray[0]);

                curCommand.runCommand(context, WordArray);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
