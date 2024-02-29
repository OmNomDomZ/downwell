package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class SqrtTest {
    @Test
    public void sqrtTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("SQRT");
        context.push(16.0);

        Calculator calculator = new Calculator();
        calculator.calculate(context, args);

        double result = context.pop();

        Assert.assertEquals(4.0, result, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("SQRT", "a");

        String error = "no error";
        try {
            calculator.calculate(context, args);
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

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Недостаточно аргументов при вызове команды: SQRT";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
