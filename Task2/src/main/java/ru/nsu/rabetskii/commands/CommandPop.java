package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;
import ru.nsu.rabetskii.exceptions.MyEmptyStackException;
import ru.nsu.rabetskii.exceptions.IncorrectNumOfArgsException;

import java.util.EmptyStackException;
import java.util.List;

public class CommandPop implements Command {
    public void runCommand(ExecutionContext context, List<String> args){
        if (args.size() == 1){
            try {
                context.pop();
            }catch (EmptyStackException e){
                throw new MyEmptyStackException(args.getFirst());
            }
        }
        else{
            throw new IncorrectNumOfArgsException(args.getFirst());
        }
    }
}
