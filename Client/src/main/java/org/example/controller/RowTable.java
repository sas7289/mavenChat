package org.example.controller;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class RowTable {
    SimpleStringProperty timeOfMessage;
    SimpleStringProperty message;

    public RowTable(Date timeOfMessage, String message) {
        this.timeOfMessage = new SimpleStringProperty(String.valueOf(timeOfMessage));
        this.message = new SimpleStringProperty(message);
    }

    public String getTimeOfMessage() {
        return timeOfMessage.get();
    }

    public SimpleStringProperty timeOfMessageProperty() {
        return timeOfMessage;
    }

    public void setTimeOfMessage(String timeOfMessage) {
        this.timeOfMessage.set(timeOfMessage);
    }

    public String getMessage() {
        return message.get();
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }
}
