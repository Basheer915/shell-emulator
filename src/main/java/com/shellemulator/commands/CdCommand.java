package com.shellemulator.commands;

import java.util.List;

public class CdCommand implements Command{
    @Override
    public String getName() {
        return "cd";
    }

    @Override
    public String execute(List<String> arguments) {
        if (arguments.size() > 1) {
            return "Wrong arguments for command: cd";
        }
        return arguments.toString();
    }
}
