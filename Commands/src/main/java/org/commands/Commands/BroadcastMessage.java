package org.commands.Commands;

public class BroadcastMessage extends Command {
    String message;

    public BroadcastMessage(String message) {
        super.type = CommandsType.BroadcastMessage;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public CommandsType getType() {
        return type;
    }
}
