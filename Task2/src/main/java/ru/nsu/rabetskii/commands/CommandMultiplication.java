package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;
import ru.nsu.rabetskii.exceptions.InsufficientArgumentsException;
import ru.nsu.rabetskii.exceptions.MyEmptyStackException;
import ru.nsu.rabetskii.exceptions.IncorrectNumOfArgsException;

import java.util.EmptyStackException;
import java.util.List;

public class CommandMultiplication extends Command {
    public void runCommand(ExecutionContext context, List<String> args){
        if (args.size() == 1){
            try {
                Double var1 = context.pop();
                Double var2 = context.pop();
                context.push(var1 * var2);
            }catch (EmptyStackException e){
                throw new InsufficientArgumentsException(args.getFirst());
            }
        }
        else{
            throw new IncorrectNumOfArgsException(args.getFirst());
        }
    }
}