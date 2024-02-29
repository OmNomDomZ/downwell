package ru.nsu.rabetskii;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

public class ReadFile {
    private Calculator calculator = new Calculator();
    private final ExecutionContext context = new ExecutionContext();
    private CommandFactory factory = new CacheCommandFactory(new DefaultCommandFactory());
    public void fileReader(String fileName) {
        try(Scanner scanner = new Scanner(new FileReader(fileName))){
            while(scanner.hasNext()){
                String line = scanner.nextLine();
                String[] wordArray = line.split(" ");
                List<String> args = List.of(wordArray);

                calculator.calculate(context, args, factory);
            }
        } catch (RuntimeException | FileNotFoundException e) {
            System.out.println("Произошла ошибка! " + e.getMessage());
        }
    }
}
