package org.commands.Commands;

public class AuthAnswer extends Command {
    String username;

    public AuthAnswer(String username) {
        super.type = CommandsType.AuthAnswer;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
