package org.example.model;

import org.commands.Commands.*;
import org.example.Client;
import org.example.controller.MainController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Network implements Serializable {
    String username;
    Socket socket;
    MainController mainController;
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

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public boolean connection() {
        try {
            socket = new Socket(serverHost, serverPort);
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());

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

    synchronized public void sendMessageToServer(String date, String message) {
        message = message + System.lineSeparator();
        archiveMessages.addMessageToArchive(date + username, message);
        Command command = new BroadcastMessage(date + username, message);
        try {
//            Client.showErrorMessage("-------------------");
            objectOutputStream.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public void sendMessageToServer(Command command) {
        try {
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
                            archiveMessages.addMessageToArchive(broadcastMessage.getAuthor(), broadcastMessage.getMessage());
                            Client.mainController.addMessageToTable(broadcastMessage.getAuthor(), broadcastMessage.getMessage());
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    synchronized public void waitAnswer() {
        try {
            Command command = (Command) objectInputStream.readObject();
            if (command.getType() == CommandsType.AuthAnswer) {
                AuthAnswer authAnswer = (AuthAnswer) command;
                if (authAnswer.getUsername() != null) {
                    this.setUsername(authAnswer.getUsername());
                    archiveMessages = new ArchiveMessages(String.format("history_%s.txt", username), -1);
                    archiveMessages.addListToQueue(authAnswer.getMessagesStore());
                    showHistory(authAnswer.getMessagesStore());
                    Client.goToMainWindow();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void showHistory(ArrayList<ArrayList<String>> messagesList) {
        if (messagesList == null) {
            return;
        }
        for (ArrayList<String> s : messagesList) {
            mainController.addMessageToTable(s.get(0), s.get(1));
        }
    }

    public void archiving() {
        archiveMessages.archiving();
    }
}
