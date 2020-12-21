package org.example.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Network {
    String username;
    Socket socket;
    static int serverPort = 8189;
    static String serverIp = "localhost";

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;


    public Network() {
        this(serverIp, serverPort);
    }

    public Network(String ip, int port) {
        serverIp = ip;
        serverPort = port;
    }

    public boolean connection() throws IOException {
        socket = new Socket(serverIp, serverPort);
        if(socket.isConnected()) {
            this.objectInputStream = (ObjectInputStream) socket.getInputStream();
            this.objectOutputStream = (ObjectOutputStream) socket.getOutputStream();
            return true;
        }
        return false;
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
