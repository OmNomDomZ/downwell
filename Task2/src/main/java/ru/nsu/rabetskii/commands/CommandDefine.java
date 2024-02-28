package ru.nsu.rabetskii.commands;

import ru.nsu.rabetskii.ExecutionContext;
import ru.nsu.rabetskii.exceptions.IncorrectNumOfArgsException;
import ru.nsu.rabetskii.exceptions.IncorrectVarNameException;
import ru.nsu.rabetskii.exceptions.StrIsNotDoubleException;

import java.util.List;

public class CommandDefine extends Command {
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
        if (args.size() == 3 && Character.isLetter(args.get(1).charAt(0)) && isStingDouble(args.get(2))){
            context.define(args.get(1), Double.parseDouble(args.get(2)));
        }
        else if(args.size() != 3){
            throw new IncorrectNumOfArgsException(args.getFirst());
        }
        else if(!Character.isLetter(args.get(1).charAt(0))){
            throw new IncorrectVarNameException(args.getFirst());
        }
        else if(!isStingDouble(args.get(2))){
            throw new StrIsNotDoubleException(args.getFirst());
        }
    }
}
