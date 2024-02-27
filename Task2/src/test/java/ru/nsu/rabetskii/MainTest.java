package ru.nsu.rabetskii;

import org.junit.Assert;
import org.junit.Test;
import ru.nsu.rabetskii.commands.*;

import java.util.Arrays;
import java.util.List;

public class MainTest {

    @Test
    public void defineTest() {
        ExecutionContext context = new ExecutionContext();
        Calculator calculator = new Calculator();
        List<String> arguments = Arrays.asList("DEFINE", "a", "1");

        calculator.calculate(context, arguments);
        double definedValue = context.getDefinedValue("a");
        Assert.assertEquals(1.0, definedValue, 0);
    }

    @Test
    public void plusTest(){
        ExecutionContext context = new ExecutionContext();
        context.push(2.0);
        context.push(3.0);

        Command command = new CommandPlus();
        command.runCommand(context, null);

        double sum = context.pop();

        Assert.assertEquals(5.0, sum, 0);
    }

    @Test
    public void minusTest(){
        ExecutionContext context = new ExecutionContext();
        context.push(2.5);
        context.push(2.0);

        Command command = new CommandMinus();
        command.runCommand(context, null);

        double difference = context.pop();

        Assert.assertEquals(-0.5, difference, 0);
    }

    @Test
    public void divisionTest(){
        ExecutionContext context = new ExecutionContext();
        context.push(2.5);
        context.push(10.0);

        Command command = new CommandDivision();
        command.runCommand(context, null);

        double quotient = context.pop();

        Assert.assertEquals(4.0, quotient, 0);
    }

    @Test
    public void multiplicationTest(){
        ExecutionContext context = new ExecutionContext();
        context.push(2.5);
        context.push(10.0);

        Command command = new CommandMultiplication();
        command.runCommand(context, null);

        double product = context.pop();

        Assert.assertEquals(25.0, product, 0);
    }

    @Test
    public void sqrtTest(){
        ExecutionContext context = new ExecutionContext();
        context.push(16.0);

        Command command = new CommandSQRT();
        command.runCommand(context, null);

        double result = context.pop();

        Assert.assertEquals(4.0, result, 0);
    }
}