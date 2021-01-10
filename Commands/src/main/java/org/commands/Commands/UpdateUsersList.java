package org.commands.Commands;

import java.io.Serializable;
import java.util.Set;

public class UpdateUsersList extends Command implements Serializable {
    Set<String> usersSet;


    public UpdateUsersList(Set<String> usersSet) {
        super(CommandsType.UpdateUsersList);
        this.usersSet = usersSet;
    }

    public Set<String> getUsersSet() {
        return usersSet;
    }
}
