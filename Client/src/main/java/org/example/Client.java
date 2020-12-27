package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.commands.Commands.Command;
import org.commands.Commands.Disconnect;
import org.example.controller.AuthController;
import org.example.controller.MainController;
import org.example.model.Network;

import java.io.IOException;

public class Client extends Application {
    public static Stage primaryStage;
    public static Stage authStage;
    public static String username;
    public static MainController mainController;
    private static Network network;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        network = new Network();
        if (!network.connection()) {
            showErrorMessage("Ошибка подключения");
            return;
        }
        createMainWindow(network);
        showAuthWindow(network);


    }

    private void createMainWindow(Network network) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(org.example.Client.class.getResource("mainWindow.fxml"));
        try {
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("ClientChat");
        mainController= loader.getController();
        network.setMainController(mainController);
        mainController.setNetwork(network);
        mainController.setClient(this);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setOnCloseRequest(windowEvent -> {
            network.archiving();
            network.sendMessageToServer(Command.createDisconnectCmd());
            primaryStage.close();
        });
    }


    public void showAuthWindow(Network network) throws IOException {
        authStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(org.example.Client.class.getResource("authWindow.fxml"));
        authStage.setScene(new Scene(fxmlLoader.load()));
        AuthController authController = fxmlLoader.getController();
        authController.setAuthStage(authStage);
        authController.setNetwork(network);

        authStage.showAndWait();
    }


    public static void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void goToMainWindow() {
        authStage.close();
        Client.primaryStage.show();
        Client.mainController.getUsernameOnClient().setText(network.getUsername());
        network.waitMessage();
    }


    public static void main(String[] args) {
        launch();
    }
}
