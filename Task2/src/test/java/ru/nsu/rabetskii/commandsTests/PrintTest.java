package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;

import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.CommandFactory;
import ru.nsu.rabetskii.DefaultCommandFactory;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class PrintTest {
    private final CommandFactory factory = new DefaultCommandFactory();
    @Test
    public void printTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("PRINT");
        context.push(16.0);

        factory.createCommand(args.getFirst());

        Calculator calculator = new Calculator();
        calculator.calculate(context, args, factory);

        double result = context.pop();

        Assert.assertEquals(16.0, result, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("PRINT", "a");

        factory.createCommand(args.getFirst());

        String error = "no error";
        try {
            calculator.calculate(context, args, factory);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неправильное количество аргументов в команде: PRINT";
        Assert.assertEquals(expectedErrorMessage, error);
    }

    @Test
    public void insufficientArguments(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("PRINT");

        factory.createCommand(args.getFirst());

        String error = "no error";
        try {
            calculator.calculate(context, args, factory);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Пустой стек во время вызова комманды: PRINT";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
