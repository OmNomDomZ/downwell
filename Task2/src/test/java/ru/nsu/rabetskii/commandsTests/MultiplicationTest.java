package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;

import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.CommandFactory;
import ru.nsu.rabetskii.DefaultCommandFactory;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;
public class MultiplicationTest {
    private final CommandFactory factory = new DefaultCommandFactory();
    @Test
    public void multiplicationTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("*");
        context.push(2.5);
        context.push(10.0);

        factory.createCommand(args.getFirst());

        Calculator calculator = new Calculator();
        calculator.calculate(context, args, factory);

        double product = context.pop();

        Assert.assertEquals(25.0, product, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("*", "a");

        factory.createCommand(args.getFirst());

        String error = "no error";
        try {
            calculator.calculate(context, args, factory);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неправильное количество аргументов в команде: *";
        Assert.assertEquals(expectedErrorMessage, error);
    }

    @Test
    public void insufficientArguments(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("*");
        context.push(2.5);

        factory.createCommand(args.getFirst());

        String error = "no error";
        try {
            calculator.calculate(context, args, factory);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Недостаточно аргументов при вызове команды: *";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
