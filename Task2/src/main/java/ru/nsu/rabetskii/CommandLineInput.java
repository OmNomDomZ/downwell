package ru.nsu.rabetskii;

import ru.nsu.rabetskii.exceptions.CommandCreationException;

import java.util.List;
import java.util.Scanner;

public class CommandLineInput {
    private Calculator calculator = new Calculator();
    private final ExecutionContext context = new ExecutionContext();
    private final CommandFactory factory = new CacheCommandFactory(new DefaultCommandFactory());
    private boolean exit = false;

    public void getInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!exit) {
                String inputString = scanner.nextLine();
                String[] wordArray = inputString.split(" ");
                List<String> args = List.of(wordArray);

                if (!inputString.equalsIgnoreCase("finish")) {
                    try {
                        calculator.calculate(context, args, factory);
                    } catch (RuntimeException e){
                        System.out.println("Произошла ошибка! " + e.getMessage());
                    }
                } else {
                    exit = true;
                    return;
                }
            }
        }
    }
}
