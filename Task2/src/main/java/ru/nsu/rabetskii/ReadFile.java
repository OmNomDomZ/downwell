package ru.nsu.rabetskii;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;

import ru.nsu.rabetskii.commands.*;

public class ReadFile {
    private Calculator calculator = new Calculator();
    private final ExecutionContext context = new ExecutionContext();
    public void FileReader(String fileName) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new FileReader(fileName))){
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String[] WordArray = line.split(" ");

                calculator.calculate(context, WordArray);
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
