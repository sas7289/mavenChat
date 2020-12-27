package org.example.controller;

import javafx.beans.property.SimpleStringProperty;
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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    );



    @FXML
    public void initialize() {
        sendMessageButton.setOnAction(event -> {
            String date = dateToString(new GregorianCalendar().getTime()) + "|";
            String message = textArea.getText();
            addMessageToTable(date + " Я", message);
            sendMessageToServer(date, message);
            textArea.clear();
        });

        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)) {
                    String date = dateToString(new GregorianCalendar().getTime()) + "|";
                    String message = textArea.getText();
                    addMessageToTable(date + " Я", message);
                    sendMessageToServer(date, message);
                    textArea.clear();
                }
            }
        });

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("stringDate"));
        messagesColumn.setCellValueFactory(new PropertyValueFactory<>("message"));


        tableForMessages.setItems(listOfMessages);
    }

    public Label getUsernameOnClient() {
        return usernameOnClient;
    }

    @FXML
    public void addMessageToTable(String author, String message) {
        if(message.isBlank()) {return;}
        String splitAuthor[] = author.split("\\|");
        message = message.replaceAll("$\n", "");
        listOfMessages.add(new RowTable(splitAuthor[0], String.format ("%s: %s", splitAuthor[1], message)));
    }


    private void sendMessageToServer(String date, String message) {
        message = message.replaceAll("$\n", "");
        network.sendMessageToServer(date, message);
    }

    public String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy HH:mm");
        return dateFormat.format(date);
    }


    public void setClient(Client client) {
        this.client = client;
    }
}
