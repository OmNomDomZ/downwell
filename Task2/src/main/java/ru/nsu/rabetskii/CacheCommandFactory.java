package ru.nsu.rabetskii;

import ru.nsu.rabetskii.commands.*;
import ru.nsu.rabetskii.exceptions.*;

import java.util.HashMap;
import java.util.Map;
public class CacheCommandFactory implements CommandFactory {
    private Command command;
    private CommandFactory factory;
    private Map<String, Command> map = new HashMap<String, Command>();

    public CacheCommandFactory(CommandFactory otherFactory){
        factory = otherFactory;
    }

    public Command createCommand(String commandName) throws CommandCreationException {
        if (map.containsKey(commandName)){
            return map.get(commandName);
        }
        else{
            command = factory.createCommand(commandName);
            map.put(commandName, command);
            return command;
        }
    }
}
