package org.commands.Commands;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Command implements Serializable {
    CommandsType type;

    public Command(CommandsType commandsType) {
        type = commandsType;
    }
    public CommandsType getType() {
        return this.type;
    }


    public static Command createAuthCmd(String login, String password) {
        return new AuthRequest(login, password);
    }

    public static Command createDisconnectCmd() {
        return new Disconnect();
    }

    public static Command createPrivateMessageCmd(String targetUser, String message) {
        return new PrivateMessage(targetUser, message);
    }

    public static Command createBroadcastMessageCmd(String message, String author) {
        return new BroadcastMessage(message, author);
    }

    public static Command createServerMessageCmd(String message) {
        return new ServerMessage(message);
    }

    public static Command createAnswerAuthorization(String username, ArrayList<ArrayList<String>> messagesList) {
        return new AuthAnswer(username, messagesList);
    }

    public static Command createUpdateUsersList(Set<String> usersSet) {
        return new UpdateUsersList(usersSet);
    }
}
