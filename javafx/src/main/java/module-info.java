module com.svalero.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires javafx.base;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.swing;
    opens com.svalero.javafx.models to javafx.base, com.google.gson;

    exports com.svalero.javafx.models;
    opens com.svalero.javafx to javafx.fxml;
    exports com.svalero.javafx;
    exports com.svalero.javafx.tasks;
    opens com.svalero.javafx.tasks to javafx.fxml;
    exports com.svalero.javafx.controllers;
    opens com.svalero.javafx.controllers to javafx.fxml;
}