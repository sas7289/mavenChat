package org.commands.Commands;

public class AuthRequest extends Command {
    String login;
    String password;

    public AuthRequest(String login, String password) {
        super.type = CommandsType.Auth;
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
