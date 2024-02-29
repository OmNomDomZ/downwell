package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;
import ru.nsu.rabetskii.exceptions.IncorrectNumOfArgsException;
import ru.nsu.rabetskii.exceptions.MyEmptyStackException;
import ru.nsu.rabetskii.exceptions.NonExisteVariableException;

import java.util.EmptyStackException;
import java.util.List;

public class CommandPush implements Command {
    private boolean isStingDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public void runCommand(ExecutionContext context, List<String> args){
        if (args.size() == 2){
            if (isStingDouble(args.get(1))){
                context.push(Double.parseDouble(args.get(1)));
            }
            else {
                if (context.contain(args.get(1))){
                    context.push(context.getDefinedValue(args.get(1)));
                }
                else{
                    throw new NonExisteVariableException(args.getFirst());
                }
            }
        }
        else{
            throw new IncorrectNumOfArgsException(args.getFirst());
        }
    }
}