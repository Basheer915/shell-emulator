package com.shellemulator.commands;

import com.shellemulator.vfs.VirtualFileSystem;

import java.util.List;

/**
 * Changes the current directory within the virtual filesystem.
 */
public class CdCommand implements Command {

    private final VirtualFileSystem fileSystem;

    public CdCommand(VirtualFileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String getName() {
        return "cd";
    }

    @Override
    public String execute(List<String> arguments) {
        if (arguments.size() != 1) {
            return "Wrong arguments for command: cd";
        }

        String directory = arguments.get(0);

        if (!fileSystem.changeDirectory(directory)) {
            return "Directory not found or access outside VFS root: " + directory;
        }

        return "Changed directory to: "
                + fileSystem.getCurrentDirectory().getFileName();
    }
}