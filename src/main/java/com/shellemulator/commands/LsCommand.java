package com.shellemulator.commands;

import com.shellemulator.vfs.VirtualFileSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lists entries in the current virtual directory.
 */
public class LsCommand implements Command {

    private final VirtualFileSystem fileSystem;

    public LsCommand(VirtualFileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String getName() {
        return "ls";
    }

    @Override
    public String execute(List<String> arguments) {
        if (!arguments.isEmpty()) {
            return "Wrong arguments for command: ls";
        }

        Path currentDirectory = fileSystem.getCurrentDirectory();

        try (var entries = Files.list(currentDirectory)) {
            return entries
                    .map(path -> path.getFileName().toString())
                    .sorted()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException exception) {
            return "Error listing directory: " + exception.getMessage();
        }
    }
}