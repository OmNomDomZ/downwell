package ru.nsu.rabetskii;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

public class ReadFile {
    private Calculator calculator = new Calculator();
    private final ExecutionContext context = new ExecutionContext();
    public void fileReader(String fileName) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new FileReader(fileName))){
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String[] wordArray = line.split(" ");
                List<String> arguments = List.of(wordArray);

                calculator.calculate(context, arguments);
            }
        } catch (IOException e) {
            throw new FileNotFoundException(fileName);
        }
    }
}
