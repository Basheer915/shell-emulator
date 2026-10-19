package com.shellemulator.config;

import java.nio.file.Path;
/**
 * Stores the paths required to start the shell emulator.
 */
public class Configuration {
    private final Path vfsPath;
    private final Path scriptPath;

    public Configuration(Path vfsPath, Path scriptPath) {
        this.vfsPath = vfsPath;
        this.scriptPath = scriptPath;
    }
    public Path getVfsPath() {return vfsPath;}
    public Path getScriptPath() {return scriptPath;}
}