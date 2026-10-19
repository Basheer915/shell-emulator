
package com.shellemulator.vfs;

import java.nio.file.Path;

/**
 * Stores the root and current directory of the virtual filesystem.
 */
public class VirtualFileSystem {

    private final Path root;
    private Path currentDirectory;

    public VirtualFileSystem(Path root) {
        this.root = root.toAbsolutePath().normalize();
        this.currentDirectory = this.root;
    }

    public Path getRoot() {
        return root;
    }

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    public boolean changeDirectory(String directory) {
        Path target = currentDirectory.resolve(directory).normalize();

        if (!target.startsWith(root)) {
            return false;
        }

        if (!java.nio.file.Files.isDirectory(target)) {
            return false;
        }

        currentDirectory = target;
        return true;
    }
}
