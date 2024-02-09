package ru.nsu.rabetskii;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Properties;

import ru.nsu.rabetskii.commands.*;

public class ReadFile {
    private CommandFactory factory;
    private final ExecutionContext context = new ExecutionContext();
    public void FileReader(String fileName) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new FileReader(fileName))){
            while(scanner.hasNext()){

                Properties properties = new Properties();
                properties.load(new FileReader("config.properties"));
                String line = scanner.nextLine();
                String[] WordArray = line.split(" ");

                String Command = properties.getProperty(WordArray[0]);
                Class<?> CommandClass = Class.forName(Command);
                factory = (CommandFactory) CommandClass.newInstance();

//                if (!line.isEmpty()){
//                    switch (Command){
//                        case "#":
//                            factory = new CommandComment();
//                            break;
//                        case "DEFINE":
//                            factory = new CommandDefine();
//                            break;
//                        case "/":
//                            factory = new CommandDivision();
//                            break;
//                        case "-":
//                            factory = new CommandMinus();
//                            break;
//                        case "*":
//                            factory = new CommandMultiplication();
//                            break;
//                        case "+":
//                            factory = new CommandPlus();
//                            break;
//                        case "POP":
//                            factory = new CommandPop();
//                            break;
//                        case "PRINT":
//                            factory = new CommandPrint();
//                            break;
//                        case "PUSH":
//                            factory = new CommandPush();
//                            break;
//                        case "SQRT":
//                            factory = new CommandSQRT();
//                            break;
//                    }
                    factory.runCommand(context, WordArray);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
