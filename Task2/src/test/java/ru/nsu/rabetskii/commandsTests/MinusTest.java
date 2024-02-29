package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;

import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.CommandFactory;
import ru.nsu.rabetskii.DefaultCommandFactory;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;
public class MinusTest {
    private final CommandFactory factory = new DefaultCommandFactory();
    @Test
    public void minusTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("-");
        context.push(2.5);
        context.push(2.0);

        factory.createCommand(args.getFirst());

        Calculator calculator = new Calculator();
        calculator.calculate(context, args, factory);

        double difference = context.pop();

        Assert.assertEquals(-0.5, difference, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("-", "a");

        factory.createCommand(args.getFirst());

        String error = "no error";
        try {
            calculator.calculate(context, args, factory);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неправильное количество аргументов в команде: -";
        Assert.assertEquals(expectedErrorMessage, error);
    }

    @Test
    public void insufficientArguments(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("-");
        context.push(2.5);

        factory.createCommand(args.getFirst());

        String error = "no error";
        try {
            calculator.calculate(context, args, factory);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Недостаточно аргументов при вызове команды: -";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
