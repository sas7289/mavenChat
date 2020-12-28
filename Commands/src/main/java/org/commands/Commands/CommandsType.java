package org.commands.Commands;

import java.io.Serializable;

public enum CommandsType implements Serializable {
    Auth,
    AuthAnswer,
    BroadcastMessage,
    Disconnect,
    PrivateMessage,
    ServerMessage,
    UpdateUsersList
}
