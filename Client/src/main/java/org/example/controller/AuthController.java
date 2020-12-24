package org.example.controller;

import javafx.application.Platform;
import org.commands.Commands.AuthAnswer;
import org.commands.Commands.AuthRequest;
import org.commands.Commands.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Client;
import org.example.model.Network;

import java.io.IOException;

public class AuthController {
    Network network;

    Stage authStage;

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setAuthStage(Stage authStage) {
        this.authStage = authStage;
    }

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button connectButton;

    @FXML
    Button exitButton;

    @FXML
    public void initialize() {
        connectButton.setOnAction(event -> {
            auth();
        });
        exitButton.setOnAction(event -> {
            authStage.close();
        });
    }

    private void auth() {
        Platform.runLater(() -> {
            try {
                String login = loginField.getText();
                String pass = passwordField.getText();
//            Client.showErrorMessage("+++++++++++");
                network.sendAuth(login, pass);
            } catch (IOException e) {
                e.printStackTrace();
//                Client.showErrorMessage("--------------");
            }
            network.waitAnswer();
            authStage.close();
            Client.primaryStage.show();
            Client.mainController.getUsernameOnClient().setText(Client.username);
            Client.mainController.network.waitMessage();
        });
    }


}
