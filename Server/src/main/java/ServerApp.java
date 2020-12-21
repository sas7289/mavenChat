import server.DBManagement;
import server.Server;

import java.io.IOException;
import java.sql.SQLException;

public class ServerApp {


    private static Server myServer;

    public static void main(String[] args) {
        try {
            myServer = new Server();
            myServer.setStatement(DBManagement.connection());
            myServer.waitConnection();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
