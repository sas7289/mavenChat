package org.example.controller;

import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RowTable {
    SimpleStringProperty timeOfMessage;
    SimpleStringProperty stringDate;
    SimpleStringProperty message;

    public RowTable(Date timeOfMessage, String message) {
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy HH:mm");
        this.stringDate = new SimpleStringProperty(dateFormat.format(timeOfMessage));
        this.timeOfMessage = new SimpleStringProperty(String.valueOf(timeOfMessage));
        this.message = new SimpleStringProperty(message);
    }
    public RowTable(String stringDate, String message) {
        this.stringDate = new SimpleStringProperty(stringDate);
        this.message = new SimpleStringProperty(message);
    }

    public String getStringDate() {
        return stringDate.get();
    }

    public SimpleStringProperty stringDateProperty() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate.set(stringDate);
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
