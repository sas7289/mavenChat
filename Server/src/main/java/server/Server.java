package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Server {
    Statement stmt;
//    private ArrayList<ClientHandler> userHandlers = new ArrayList<>();
    private HashMap<String, ClientHandler> userHandlers = new HashMap();
    private static int PORT_SERVER = 8189;
    private ServerSocket serverSocket;

    public Server() throws IOException {
        this(PORT_SERVER);
    }

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void setStatement(Statement stmt) {
        this.stmt = stmt;
    }

    public void waitConnection() throws IOException {
        while (true) {
            System.out.println("Сервер запущен и ожидает подключения");
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> {
                try {
                    System.out.println("Пользователь подключился");
                    ClientHandler clientHandler = new ClientHandler(this, clientSocket);
                    clientHandler.handle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


    public String getUsername(String login, String password) throws SQLException {
        ResultSet rs = stmt.executeQuery(String.format("SELECT Username FROM Users WHERE Login = '%s' AND Password = '%s'", login, password));
        if (rs.next()) {
            return rs.getString("Username");
        }
        else {
            return null;
        }
    }
    public void addUser(String username, String group) throws SQLException {
        stmt.executeUpdate(String.format("INSERT INTO current_session VALUES ('%s', '%s')", username, group));
    }


    public HashMap<String, ClientHandler> getUserHandlers() {
        return userHandlers;
    }
}
