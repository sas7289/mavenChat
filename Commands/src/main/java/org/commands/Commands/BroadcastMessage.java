package org.commands.Commands;

import java.io.Serializable;

public class BroadcastMessage extends Command implements Serializable {
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
