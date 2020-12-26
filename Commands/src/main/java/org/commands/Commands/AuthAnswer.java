package org.commands.Commands;


import java.util.ArrayList;

public class AuthAnswer extends Command {
    String username;
    ArrayList<String> messagesList;
    public AuthAnswer(String username, ArrayList<String> messagesList) {
        super.type = CommandsType.AuthAnswer;
        this.username = username;
        this.messagesList = messagesList;
    }
    public void setMessagesStore(ArrayList<String> messagesList) {
        this.messagesList = messagesList;
    }

    public ArrayList<String> getMessagesStore() {
        return messagesList;
    }

    public String getUsername() {
        return username;
    }
}
