package org.commands.Commands;

public class Disconnect extends Command {

    public Disconnect() {
        super(CommandsType.Disconnect);
    }


    @Override
    public CommandsType getType() {
        return type;
    }
}
