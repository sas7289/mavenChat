package org.example.model;

import org.commands.Commands.*;
import org.example.Client;
import org.example.controller.MainController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Network implements Serializable {
    FileOutputStream fileOutputStream;
    String username;
    Socket socket;
    static int serverPort = 8199;
    static String serverHost = "localhost";
    ArchiveMessages archiveMessages;


    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public Network() {
        this(serverHost, serverPort);
    }

    public Network(String host, int port) {
        serverHost = host;
        serverPort = port;
    }

    public boolean connection() {
        try {
            socket = new Socket(serverHost, serverPort);
            this.objectOutputStream = new ObjectOutputStream (socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream (socket.getInputStream());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void authorization() {

    }


    public void setUsername(String username) {
        this.username = username;
    }


    synchronized public void sendAuth(String login, String password) throws IOException {
//        Client.showErrorMessage("------------1");
        Command command = new AuthRequest(login, password);
//        Client.showErrorMessage("------------2");
        objectOutputStream.writeObject(command);
    }

    public String getUsername() {
        return username;
    }

    synchronized public void sendMessageToServer(String message) {
        archiveMessages.addMessageToArchive(message);
        Command command = new BroadcastMessage(message, username);
        try {
//            Client.showErrorMessage("-------------------");
            objectOutputStream.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void waitMessage() {
        new Thread(() -> {
            while (true) {
                try {
                    Command command = (Command) getObjectInputStream().readObject();
                    switch (command.getType()) {
                        case BroadcastMessage:
                            BroadcastMessage broadcastMessage = (BroadcastMessage) command;
                            Client.mainController.addMessageToTable(broadcastMessage.getMessage(), broadcastMessage.getAuthor());
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    synchronized public void waitAnswer() {
            while (true) {
                try {
                    AuthAnswer authAnswer = (AuthAnswer) objectInputStream.readObject();
                    if(authAnswer.getUsername() != null) {
                        this.setUsername(authAnswer.getUsername());
                        archiveMessages = new ArchiveMessages(String.format("history_%s.txt", username), 5);
                        break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    }

    public void archiving() {
        archiveMessages.archiving();
    }
}
