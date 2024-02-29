package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;
import ru.nsu.rabetskii.exceptions.IncorrectNumOfArgsException;
import ru.nsu.rabetskii.exceptions.InsufficientArgumentsException;
import ru.nsu.rabetskii.exceptions.MyEmptyStackException;

import java.util.EmptyStackException;
import java.util.List;

public class CommandSQRT implements Command {
    public void runCommand(ExecutionContext context, List<String> args){
        if (args.size() == 1){
            try {
                Double var = context.pop();
                context.push(Math.sqrt(var));
            }catch (EmptyStackException e){
                throw new InsufficientArgumentsException(args.getFirst());
            }
        }
        else{
            throw new IncorrectNumOfArgsException(args.getFirst());
        }
    }
}