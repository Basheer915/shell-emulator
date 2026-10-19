package com.shellemulator.config;

import java.nio.file.Path;
/**
 * Parses command-line arguments used to configure the shell emulator.
 */
public class ConfigurationParser {

    private static final int EXPECTED_ARGS_COUNT = 4;
    /**
     * Parses the VFS and startup script paths from command-line arguments.
     *
     * @param args command-line arguments
     * @return the parsed configuration
     * @throws IllegalArgumentException if the arguments are missing or invalid
     */
    public Configuration parse(String[] args) {
        if (args == null || args.length != EXPECTED_ARGS_COUNT) {
            throw new IllegalArgumentException(
                    "Expected: --vfs <path> --script <path>");
        }
        if (!"--vfs".equals(args[0]) || !"--script".equals(args[2])) {
            throw new IllegalArgumentException(
                    "Expected: --vfs <path> --script <path>");
        }
        if (args[1].isBlank() || args[3].isBlank()) {
            throw new IllegalArgumentException("Paths must not be empty.");
        }
        Path vfsPath = Path.of(args[1]);
        Path scriptPath = Path.of(args[3]);
        return new Configuration(vfsPath, scriptPath);
    }
}