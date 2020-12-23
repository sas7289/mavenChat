package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.controller.AuthController;
import org.example.controller.MainController;
import org.example.model.Network;

import java.io.IOException;

public class Client extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        Network network = new Network();
        if (!network.connection()) {
            showErrorMessage("Ошибка подключения");
            return;
        }
        showAuthWindow(network);
        createMainWindow();


    }

    private void createMainWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(org.example.Client.class.getResource("mainWindow.fxml"));
        try {
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("ClientChat");
        MainController controller = loader.getController();
        controller.setClient(this);
        primaryStage.setAlwaysOnTop(true);
//        primaryStage.show();
    }


    public void showAuthWindow(Network network) throws IOException {
        Stage authStage = new Stage();
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


    public static void main(String[] args) {
        launch();
    }
}
