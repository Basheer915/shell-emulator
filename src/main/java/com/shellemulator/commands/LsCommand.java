package com.shellemulator.commands;

import java.util.List;

public class LsCommand implements Command{
    @Override
    public String getName() {
        return "ls";
    }

    @Override
    public String execute(List<String> arguments) {
        return arguments.toString();
    }
}
