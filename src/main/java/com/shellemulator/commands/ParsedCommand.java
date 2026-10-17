package com.shellemulator.commands;

import java.util.List;

/**
 * Stores the command name and its arguments after parsing user input.
 */
public class ParsedCommand {

    private final String name;
    private final List<String> arguments;

    /**
     * Creates a parsed command with the specified name and arguments.
     *
     * @param name the name of the command
     * @param arguments the arguments passed to the command
     */
    public ParsedCommand(String name, List<String> arguments) {
        this.name = name;
        this.arguments = arguments;
    }
    /**
     * Returns the command name.
     *
     * @return the command name
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the arguments of the command.
     *
     * @return the list of command arguments
     */
    public List<String> getArguments() {
        return arguments;
    }
}