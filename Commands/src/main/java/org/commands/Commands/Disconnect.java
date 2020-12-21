package org.commands.Commands;

public class Disconnect extends Command {

    public Disconnect() {
        super.type = CommandsType.Disconnect;
    }


    @Override
    public CommandsType getType() {
        return type;
    }
}
