package com.palfish.framework.command;

import java.util.concurrent.LinkedBlockingQueue;

public class MacroCommand implements Command {
    private LinkedBlockingQueue<Command> commands;

    public MacroCommand() {
        commands = new LinkedBlockingQueue<Command>();
    }

    public void add(Command command) {
        this.commands.add(command);
    }

    public void execute() {
        while (commands.size() > 0){
            Command cmd = commands.poll();
            cmd.execute();
        }
    }
}
