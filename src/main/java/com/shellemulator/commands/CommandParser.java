package com.shellemulator.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

/**
 * Parses a command line into a command name and its arguments.
 */
public class CommandParser {
    /**
     * Parses the user input and separates the command name from its arguments.
     *
     * @param input the command line entered by the user
     * @return a ParsedCommand containing the command name and arguments,
     *         or an empty command if the input is null or blank
     */
    public ParsedCommand parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new ParsedCommand("", Collections.emptyList());
        }
        String[] parts = input.trim().split("\\s+");
        String commandName = parts[0];

        List<String> arguments = parts.length > 1 ? Arrays.asList(parts).subList(1, parts.length)
                : Collections.emptyList();
        return new ParsedCommand(commandName, arguments);
    }
}
