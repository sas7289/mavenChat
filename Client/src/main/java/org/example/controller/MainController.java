package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.commands.Commands.BroadcastMessage;
import org.commands.Commands.Command;
import org.example.Client;
import org.example.model.Network;


import java.util.Date;

public class MainController  {
    Client client;

    Network network;

    @FXML
    TextArea textArea;

    @FXML
    TableView<RowTable> tableForMessages;

    @FXML
    TableColumn<RowTable, String> dateColumn;

    @FXML
    TableColumn<RowTable, String> messagesColumn;

    @FXML
    Button sendMessageButton;

    @FXML
    Label usernameOnClient;


    public void setNetwork(Network network) {
        this.network = network;
    }

    private final ObservableList<RowTable> listOfMessages = FXCollections.observableArrayList(
            new RowTable(new Date(), "HelloEPTA")
    );



    @FXML
    public void initialize() {
        sendMessageButton.setOnAction(event -> {
            String message = textArea.getText();
            addMessageToTable(message, "Я");
            sendMessageToServer(message);
            textArea.clear();
        });

        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)) {
                    String message = textArea.getText();
                    addMessageToTable(message, "Я");
                    sendMessageToServer(message);
                    textArea.clear();
                }
            }
        });

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfMessage"));
        messagesColumn.setCellValueFactory(new PropertyValueFactory<>("message"));


        tableForMessages.setItems(listOfMessages);
    }

    public Label getUsernameOnClient() {
        return usernameOnClient;
    }

    @FXML
    public void addMessageToTable(String message, String author) {
        if(message.isBlank()) {return;}
        listOfMessages.add(new RowTable(new Date(), String.format ("%s: %s", author, message)));
    }


    private void sendMessageToServer(String message) {
        network.sendMessageToServer(message);
    }



    public void setClient(Client client) {
        this.client = client;
    }
}
