package com.shellemulator;

import com.shellemulator.config.Configuration;
import com.shellemulator.config.ConfigurationParser;
import com.shellemulator.config.StartupScriptExecutor;
import com.shellemulator.vfs.VirtualFileSystem;
import com.shellemulator.ui.ShellWindow;
import javax.swing.SwingUtilities;
import java.io.IOException;
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Startup parameters:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "] = " + args[i]);
            }

            ConfigurationParser configurationParser = new ConfigurationParser();
            Configuration configuration = configurationParser.parse(args);

            VirtualFileSystem fileSystem = new VirtualFileSystem(configuration.getVfsPath());
            StartupScriptExecutor scriptExecutor = new StartupScriptExecutor(fileSystem);
            String scriptOutput = scriptExecutor.execute(configuration.getScriptPath());

            SwingUtilities.invokeLater(() -> {
                ShellWindow shellWindow = new ShellWindow(fileSystem);
                shellWindow.displayStartupOutput(scriptOutput);
                shellWindow.setVisible(true);
                shellWindow.focusInput();
            });

        } catch (IllegalArgumentException | IOException exception) {
            System.err.println("Startup error: " + exception.getMessage());
            System.exit(1);
        }
    }
}
