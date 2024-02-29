package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;

import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.CommandFactory;
import ru.nsu.rabetskii.DefaultCommandFactory;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class SqrtTest {
    private final CommandFactory factory = new DefaultCommandFactory();
    @Test
    public void sqrtTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("SQRT");
        context.push(16.0);

        factory.createCommand(args.getFirst());

        Calculator calculator = new Calculator();
        calculator.calculate(context, args, factory);

        double result = context.pop();

        Assert.assertEquals(4.0, result, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("SQRT", "a");

        factory.createCommand(args.getFirst());

        String error = "no error";
        try {
            calculator.calculate(context, args, factory);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неправильное количество аргументов в команде: SQRT";
        Assert.assertEquals(expectedErrorMessage, error);
    }

    @Test
    public void insufficientArguments(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("SQRT");

        factory.createCommand(args.getFirst());

        String error = "no error";
        try {
            calculator.calculate(context, args, factory);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Недостаточно аргументов при вызове команды: SQRT";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
