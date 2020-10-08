package com.edu;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class CommandFactory {
    public static Command command(String query) throws InvalidParameterException {
        try {
            if (query.isEmpty())
                return new StopCommand();

            var args = query.split(" ");

            var command_name = args[0];
            String[] command_args = null;

            if (args.length > 1)
                command_args = Arrays.copyOfRange(args, 1, args.length);

            switch (command_name) {
                case "add":
                    return new AddCommand(command_args);
                case "get":
                    return new GetCommand(command_args);
                case "set":
                    return new SetCommand(command_args);
                case "rmv":
                    return new RemoveCommand(command_args);
                default:
                    throw new InvalidParameterException("Invalid command name.");
            }
        } catch (Exception e) {
            throw new InvalidParameterException("Incomplete command");
        }
    }
}
