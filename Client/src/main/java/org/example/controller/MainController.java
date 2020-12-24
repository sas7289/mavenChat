package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Client;


import java.util.Date;

public class MainController  {
    Client client;

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



    private final ObservableList<RowTable> listOfMessages = FXCollections.observableArrayList(
            new RowTable(new Date(), "HelloEPTA")
    );



    @FXML
    public void initialize() {
        sendMessageButton.setOnAction(event -> {
            addMessageToTable(textArea.getText());
            textArea.clear();
        });

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfMessage"));
        messagesColumn.setCellValueFactory(new PropertyValueFactory<>("message"));


        tableForMessages.setItems(listOfMessages);
    }

    public Label getUsernameOnClient() {
        return usernameOnClient;
    }

    @FXML
    private void addMessageToTable(String message) {
        listOfMessages.add(new RowTable(new Date(), message));
    }



    public void setClient(Client client) {
        this.client = client;
    }
}
