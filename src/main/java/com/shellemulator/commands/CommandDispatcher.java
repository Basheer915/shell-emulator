package com.shellemulator.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Registers available commands and dispatches user requests to them.
 */
public class CommandDispatcher {

    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Creates a dispatcher and registers the default commands.
     */
    public CommandDispatcher() {
        registerCommand(new CdCommand());
        registerCommand(new LsCommand());
        registerCommand(new ExitCommand());
    }
    /**
     * Registers a command using its name as the lookup key.
     *
     * @param command the command to register
     */
    private void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }
    /**
     * Executes the command with the specified name and arguments.
     *
     * @param name the name of the command to execute
     * @param arguments the arguments passed to the command
     * @return the command result, or an error message if the command is unknown
     */
    public String execute(String name, List<String> arguments) {
        Command command = commands.get(name);
        if (command == null) {
            return "Unknown command: " + name;
        }
        return command.execute(arguments);
    }
}