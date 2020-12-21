package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.AuthController;
import org.example.controller.MainController;
import org.example.model.Network;

import java.io.IOException;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(/*Client.class.getResource("mainWindow.fxml")*/);
        loader.setLocation(org.example.Client.class.getResource("mainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ClientChat");
        primaryStage.show();
        MainController controller = loader.getController();
        controller.setClient(this);
        Network network = new Network();
        network.connection();
        showAuthWindow(network);
    }


    public void showAuthWindow(Network network) throws IOException {
        Stage authStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(org.example.Client.class.getResource("authWindow.fxml"));
        AuthController authController = fxmlLoader.getController();
        authController.setAuthStage(authStage);
        authController.setNetwork(network);
        authStage.setScene(new Scene(fxmlLoader.load()));

        authStage.showAndWait();
    }



    public static void main(String[] args) {
        launch();
    }
}
