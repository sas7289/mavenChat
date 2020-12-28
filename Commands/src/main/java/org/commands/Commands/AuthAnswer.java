package org.commands.Commands;


import java.util.ArrayList;

public class AuthAnswer extends Command {
    String username;
    ArrayList<ArrayList<String>> messagesList;
    public AuthAnswer(String username, ArrayList<ArrayList<String>> messagesList) {
        super(CommandsType.AuthAnswer);
        this.username = username;
        this.messagesList = messagesList;
    }
    public void setMessagesStore(ArrayList<ArrayList<String>> messagesList) {
        this.messagesList = messagesList;
    }

    public ArrayList<ArrayList<String>> getMessagesStore() {
        return messagesList;
    }

    public String getUsername() {
        return username;
    }
}
