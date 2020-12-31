package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private static final Logger logger = (Logger) LogManager.getLogger("Server");
    Statement stmt;
    private HashMap<String, ClientHandler> userHandlers = new HashMap();
    private static final int PORT_SERVER = 8199;
    private final ServerSocket serverSocket;
    private MessagesStore messagesStore;

    public Server() throws IOException {
        this(PORT_SERVER);
    }

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        File file = new File("serverHistory.txt");
        if (!file.exists()) {
            messagesStore = new MessagesStore(100);
            return;
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serverHistory.txt"));
        try {
            messagesStore = (MessagesStore) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MessagesStore getMessagesStore() {
        return messagesStore;
    }

    public void setStatement(Statement stmt) {
        this.stmt = stmt;
    }

    public void waitConnection() {
        while (true) {
            System.out.println("Сервер запущен и ожидает подключения");
            logger.info("Сервер запущен и ожидает подключения");
            try {
                Socket clientSocket = serverSocket.accept();
                Logger logger = (Logger) LogManager.getLogger();
                ClientHandler clientHandler = new ClientHandler(this, clientSocket);
                clientHandler.handle();
                logger.info("Клиент подключился");
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Ошибка при подключении клиента", e);
            }
        }
    }


    public String getUsername(String login, String password) throws SQLException {
        ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Users WHERE Login = '%s' AND Password = '%s'", login, password));
        if (rs.next()) {
            return rs.getString("Username");
        } else {
            return null;
        }
    }

    public void addUser(String username, String group) throws SQLException {
        stmt.executeUpdate(String.format("INSERT INTO Current_session VALUES ('%s', '%s')", username, group));
    }


    public HashMap<String, ClientHandler> getUserHandlers() {
        return userHandlers;
    }

    public boolean isExist(String username) {
        for (String s : userHandlers.keySet()) {
            if (s.equals(username)) {
                return true;
            }
        }
        return false;
    }
}
