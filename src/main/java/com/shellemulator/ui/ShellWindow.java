package com.shellemulator.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import com.shellemulator.vfs.VirtualFileSystem;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.shellemulator.commands.CommandDispatcher;
import com.shellemulator.commands.ParsedCommand;
import com.shellemulator.commands.CommandParser;

/**
 * Provides the graphical window for the shell emulator.
 */
public class ShellWindow extends JFrame {
    private final CommandParser parser = new CommandParser();
    private final CommandDispatcher dispatcher;
    private final JTextArea outputArea;
    private final JTextField inputField;

    /**
     * Creates the shell window and initializes its components.
     */
    public ShellWindow(VirtualFileSystem filesystem) {
        this.dispatcher = new CommandDispatcher(filesystem);
        setTitle(createWindowTitle());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        outputArea = createOutputArea();
        inputField = new JTextField();
        inputField.addActionListener(event -> processInput());

        setupLayout();
    }
    /**
     * Displays the output produced by the startup script.
     *
     * @param text the startup dialogue to display
     */
    public void displayStartupOutput(String text) {
        if (text != null && !text.isBlank()) {
            outputArea.append(text);
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        }
    }

    /**
     * Sets focus to the command input field.
     */
    public void focusInput() {
        inputField.requestFocusInWindow();
    }

    private void processInput() {
        String input = inputField.getText();
        ParsedCommand command = parser.parse(input);

        if (command.getName().isEmpty()) {
            inputField.setText("");
            return;
        }

        String result = dispatcher.execute(command.getName(), command.getArguments());

        outputArea.append("> " + input + "\n");
        outputArea.append(result + "\n");

        if (command.getName().equals("exit") && command.getArguments().isEmpty()) {
            dispose();
        }

        inputField.setText("");
    }

    private JTextArea createOutputArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);
        setContentPane(panel);
    }

    private String createWindowTitle() {
        String username = System.getProperty("user.name");
        String hostname = getHostName();
        return "Эмулятор - [" + username + "@" + hostname + "]";
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}