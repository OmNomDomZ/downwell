package ru.nsu.rabetskii;

import java.util.List;
import java.util.Scanner;

public class CommandLineInput {
    private Calculator calculator = new Calculator();
    private final ExecutionContext context = new ExecutionContext();
    private boolean exit = false;

    public void getInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!exit) {
                String inputString = scanner.nextLine();
                String[] wordArray = inputString.split(" ");
                List<String> arguments = List.of(wordArray);

                if (!inputString.equalsIgnoreCase("finish")) {
                    try {
                        calculator.calculate(context, arguments);
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
