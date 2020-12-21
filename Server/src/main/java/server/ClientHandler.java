package server;

import org.commands.Commands.AuthRequest;
import org.commands.Commands.Command;
import org.commands.Commands.PrivateMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Map;

public class ClientHandler {
    String username;
    Server server;
    Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ClientHandler(Server server, Socket socket) {
        this.socket = socket;
    }

    public void handle() throws IOException {
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        try {
            waitMessage();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void waitMessage() throws IOException, ClassNotFoundException, SQLException {
        while (true) {
            Command command = (Command) objectInputStream.readObject();
            switch (command.getType()) {
                case Auth:
                    AuthRequest authRequest = (AuthRequest) command;
                    username = server.getUsername(authRequest.getLogin(), authRequest.getPassword());
                    if(username != null) {
                        server.addUser(username, null);
                        server.getUserHandlers().put(username, this);
                        objectOutputStream.writeObject(Command.createAnswerAuthorization(username));
                    }
                case BroadcastMessage:
                    for ( Map.Entry<String, ClientHandler> pair: server.getUserHandlers().entrySet()) {
                        pair.getValue().sendCommand(command);
                    }
                case PrivateMessage:
                    PrivateMessage privateMessage = (PrivateMessage) command;
                    ClientHandler targetHandler = server.getUserHandlers().get(privateMessage.getTargerUser());
                    targetHandler.sendCommand(command);
            }
        }
    }


    private void sendCommand(Command command) throws IOException {
        objectOutputStream.writeObject(command);
    }
}
