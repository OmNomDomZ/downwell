package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;

public class PopTest {
    @Test
    public void popTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("POP");
        context.push(1.0);
        context.push(2.0);

        Calculator calculator = new Calculator();
        calculator.calculate(context, args);

        double result = context.pop();

        Assert.assertEquals(1.0, result, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("POP", "1");

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неправильное количество аргументов в команде: POP";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
