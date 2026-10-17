package com.shellemulator.commands;

import java.util.List;

public class ExitCommand implements Command{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String execute(List<String> arguments) {
        if (!arguments.isEmpty()) {
            return "Wrong arguments for command: exit";
        }
        return "Exiting...";
    }
}
