package ru.nsu.rabetskii;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CommandLoader {
    private String[] commands;
    public String[] load(final String name) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new FileReader(name))){
            String line;
            while (scanner.hasNext()){
                line = scanner.nextLine();
                commands = line.split("(?U)\\W+");
            }
            return commands;
        }
    }
}
