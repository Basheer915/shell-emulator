package com.shellemulator.commands;
import java.util.List;
/**
 * Defines the interface for shell commands.
 */
public interface Command {
    String getName();
    String execute(List<String> arguments);
}