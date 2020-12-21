module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires Commands;

    opens org.example.controller to javafx.fxml, javafx.base;
    exports org.example;
}