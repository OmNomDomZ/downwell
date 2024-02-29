package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.List;
public class DivisionTest {
    @Test
    public void divisionTest(){
        ExecutionContext context = new ExecutionContext();
        List<String> args = List.of("/");
        context.push(2.5);
        context.push(10.0);

        Calculator calculator = new Calculator();
        calculator.calculate(context, args);

        double quotient = context.pop();

        Assert.assertEquals(4.0, quotient, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("/", "a");

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неправильное количество аргументов в команде: /";
        Assert.assertEquals(expectedErrorMessage, error);
    }

    @Test
    public void insufficientArguments(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("/");
        context.push(2.5);

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Недостаточно аргументов при вызове команды: /";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
