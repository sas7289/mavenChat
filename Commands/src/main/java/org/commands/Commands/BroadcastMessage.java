package org.commands.Commands;

import java.io.Serializable;

public class BroadcastMessage extends Command implements Serializable {
    String author;
    String message;

    public BroadcastMessage(String message, String author) {
        super.type = CommandsType.BroadcastMessage;
        this.message = message;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public CommandsType getType() {
        return type;
    }
}
