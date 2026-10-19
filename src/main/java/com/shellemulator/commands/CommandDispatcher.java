
package com.shellemulator.commands;

import com.shellemulator.vfs.VirtualFileSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registers available commands and dispatches user requests to them.
 */
public class CommandDispatcher {

    private final Map<String, Command> commands = new HashMap<>();

    /**
     * Creates a dispatcher and registers the default commands.
     *
     * @param fileSystem the virtual filesystem used by commands
     */
    public CommandDispatcher(VirtualFileSystem fileSystem) {
        registerCommand(new CdCommand(fileSystem));
        registerCommand(new LsCommand(fileSystem));
        registerCommand(new ExitCommand());
    }

    private void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Executes the command with the specified name and arguments.
     *
     * @param name the command name
     * @param arguments the command arguments
     * @return the command result or an error message
     */
    public String execute(String name, List<String> arguments) {
        Command command = commands.get(name);

        if (command == null) {
            return "Unknown command: " + name;
        }

        return command.execute(arguments);
    }
}
