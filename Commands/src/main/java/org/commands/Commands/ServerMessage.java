package org.commands.Commands;

public class ServerMessage extends Command {
    String message;

    public ServerMessage(String message) {
        super(CommandsType.ServerMessage);
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
