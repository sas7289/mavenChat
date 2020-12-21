package org.commands.Commands;

public class PrivateMessage extends Command {
    String message;
    String targerUser;

    public PrivateMessage(String targerUser ,String message) {
        super.type = CommandsType.PrivateMessage;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTargerUser() {
        return targerUser;
    }

    @Override
    public CommandsType getType() {
        return type;
    }
}
