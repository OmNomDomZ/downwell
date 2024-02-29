package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.ExecutionContext;
import ru.nsu.rabetskii.commands.*;

import java.util.List;

public class PrintTest {
    @Test
    public void printTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("PRINT");
        context.push(16.0);

        Calculator calculator = new Calculator();
        calculator.calculate(context, args);

        double result = context.pop();

        Assert.assertEquals(16.0, result, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("PRINT", "a");

        String error = "no error";
        try {
            calculator.calculate(context, args);
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

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Пустой стек во время вызова комманды: PRINT";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
