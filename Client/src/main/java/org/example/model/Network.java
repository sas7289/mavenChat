package org.example.model;

import org.commands.Commands.*;
import org.example.Client;
import org.example.controller.MainController;

import java.io.*;
import java.net.Socket;

public class Network {
    String username;
    Socket socket;
    static int serverPort = 8199;
    static String serverHost = "localhost";


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

    synchronized public void sendMessageToServer(String message) {
        Command command = new BroadcastMessage(message);
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
                            Client.mainController.addMessageToTable(broadcastMessage.getMessage());
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
                        Client.username = authAnswer.getUsername();
//                        Client.showErrorMessage("waitanswer");
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
    }
}
