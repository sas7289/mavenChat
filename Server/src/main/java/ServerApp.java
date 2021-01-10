import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.commands.Commands.AuthRequest;
import org.commands.Commands.Command;
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
