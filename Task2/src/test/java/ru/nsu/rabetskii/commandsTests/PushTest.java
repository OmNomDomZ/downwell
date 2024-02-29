package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class PushTest {
    @Test
    public void pushTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("PUSH", "16");

        Calculator calculator = new Calculator();
        calculator.calculate(context, args);

        double result = context.pop();

        Assert.assertEquals(16.0, result, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("PUSH");

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неправильное количество аргументов в команде: PUSH";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
