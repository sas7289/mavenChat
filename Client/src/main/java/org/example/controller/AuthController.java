package org.example.controller;

import org.commands.Commands.AuthAnswer;
import org.commands.Commands.AuthRequest;
import org.commands.Commands.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
            try {
                sendAuth();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(event -> {
            authStage.close();
        });
    }


    public void sendAuth() throws IOException {
        Command command = new AuthRequest(loginField.getText(), passwordField.getText());
        network.getObjectOutputStream().writeObject(command);
    }

    public void waitAnswer() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    AuthAnswer authAnswer = (AuthAnswer) network.getObjectInputStream().readObject();
                    if(authAnswer.getUsername() != null) {
                        network.setUsername(authAnswer.getUsername());
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            authStage.close();
        });
        thread.start();
    }



}
