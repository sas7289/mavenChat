package org.example.model;

import com.google.inject.internal.ErrorsException;
import org.example.Client;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;

public class Network {
    String username;
    Socket socket;
    static int serverPort = 8199;
    static String serverHost = "localhost";


    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;


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
}
