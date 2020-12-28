package org.commands.Commands;

import java.io.Serializable;

public class AuthRequest extends Command implements Serializable {
    String login;
    String password;

    public AuthRequest(String login, String password) {
        super(CommandsType.Auth);
        this.login = login;
        this.password = password;
    }



    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
