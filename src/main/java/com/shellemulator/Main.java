package com.shellemulator;

import com.shellemulator.ui.ShellWindow;
import javax.swing.SwingUtilities;

/**
 * Starts the ShellEmulator application.
 */
public class Main {
    /**
     * Creates and displays the shell emulator window on the Swing
     * event dispatch thread.
     *
     * @param args command-line arguments supplied when starting the application
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShellWindow window = new ShellWindow();
            window.setVisible(true);
            window.focusInput();
        });
    }
}