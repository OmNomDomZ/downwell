package ru.nsu.rabetskii.commandsTests;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.rabetskii.Calculator;
import ru.nsu.rabetskii.ExecutionContext;

import java.util.Arrays;
import java.util.List;

public class DefineTest {
    @Test
    public void defineTest() {
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("DEFINE", "a", "1");

        calculator.calculate(context, args);
        double definedValue = context.getDefinedValue("a");
        Assert.assertEquals(1.0, definedValue, 0);
    }

    @Test
    public void incorrectNumOfArgs(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("DEFINE", "a");

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неправильное количество аргументов в команде: DEFINE";
        Assert.assertEquals(expectedErrorMessage, error);
    }

    @Test
    public void incorrectVarName(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("DEFINE", "1", "1");

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Неверное имя переменной в команде: DEFINE";
        Assert.assertEquals(expectedErrorMessage, error);
    }

    @Test
    public void strIsNotDouble(){
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> args = List.of("DEFINE", "a", "a");

        String error = "no error";
        try {
            calculator.calculate(context, args);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        String expectedErrorMessage = "Переданная строка не является числом : DEFINE";
        Assert.assertEquals(expectedErrorMessage, error);
    }
}
