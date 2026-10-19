package com.shellemulator.config;

import com.shellemulator.commands.CommandDispatcher;
import com.shellemulator.commands.CommandParser;
import com.shellemulator.commands.ParsedCommand;
import com.shellemulator.vfs.VirtualFileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Executes shell commands from a startup script.
 */
public class StartupScriptExecutor {

    private final CommandParser parser = new CommandParser();
    private final CommandDispatcher dispatcher;

    public StartupScriptExecutor(VirtualFileSystem fileSystem) {
        this.dispatcher = new CommandDispatcher(fileSystem);
    }

    /**
     * Executes commands from a script and stops at the first error.
     *
     * @param scriptPath path to the startup script
     * @return the dialogue containing commands and their results
     * @throws IOException if the script cannot be read
     */
    public String execute(Path scriptPath) throws IOException {
        List<String> lines = Files.readAllLines(scriptPath);
        StringBuilder output = new StringBuilder();

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            ParsedCommand command = parser.parse(line);
            output.append("> ").append(line)
                    .append(System.lineSeparator());

            String result = dispatcher.execute(
                    command.getName(),
                    command.getArguments()
            );

            output.append(result).append(System.lineSeparator());

            if (isError(result)) {
                break;
            }

            if ("exit".equals(command.getName())
                    && command.getArguments().isEmpty()) {
                break;
            }
        }

        return output.toString();
    }

    private boolean isError(String result) {
        return result.startsWith("Unknown command:")
                || result.startsWith("Wrong arguments for command:")
                || result.startsWith("Directory not found or access outside VFS root:")
                || result.startsWith("Error listing directory:");
    }
}