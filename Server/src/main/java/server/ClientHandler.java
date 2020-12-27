package server;

import org.commands.Commands.AuthRequest;
import org.commands.Commands.BroadcastMessage;
import org.commands.Commands.Command;
import org.commands.Commands.PrivateMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class ClientHandler {
    String username;

    Server server;
    Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    public ClientHandler(Server server, Socket socket) {
        this.socket = socket;
        this.server = server;
    }

    public void handle() throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        new Thread(() -> {
            try {
                System.out.println("Жду сообщений от пользователя");
                waitMessage();
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getUsername() {
        return username;
    }

    private void waitMessage() throws IOException, ClassNotFoundException, SQLException {
        mark: while (true) {
            Command command = null;
            try {
                command = (Command) objectInputStream.readObject();
            }
            catch (ClassNotFoundException e) {
                System.out.println("Пришёл неизвестный объект");
            }
            switch (command.getType()) {
                case Auth:
                    AuthRequest authRequest = (AuthRequest) command;
                    username = server.getUsername(authRequest.getLogin(), authRequest.getPassword());
                    if (username != null && !server.isExist(username)) {
//                        server.addUser(username, null);
                        server.getUserHandlers().put(username, this);
                        objectOutputStream.writeObject(Command.createAnswerAuthorization(username, server.getMessagesStore().getStore()));
                    }
                    else {
                        objectOutputStream.writeObject(Command.createAnswerAuthorization(null, null));
                    }
                    break;
                case BroadcastMessage:
                    BroadcastMessage broadcastMessage = (BroadcastMessage) command;
                    server.getMessagesStore().addMessage(broadcastMessage.getMessage());
                    for (Map.Entry<String, ClientHandler> pair : server.getUserHandlers().entrySet()) {
                        if(pair.getValue().getUsername().equals(this.getUsername())) {continue;}
                        pair.getValue().sendCommand(command);
                    }
                    break;
                case PrivateMessage:
                    PrivateMessage privateMessage = (PrivateMessage) command;
                    ClientHandler targetHandler = server.getUserHandlers().get(privateMessage.getTargerUser());
                    targetHandler.sendCommand(command);
                    break;
                case Disconnect:
                    Iterator iterator = server.getUserHandlers().keySet().iterator();
                    while (iterator.hasNext()) {
                        if(iterator.next().equals(username)) {
                            iterator.remove();
                            break mark;
                        }
                    }
            }
        }
    }


    private void sendCommand(Command command) throws IOException {
        objectOutputStream.writeObject(command);
    }
}
