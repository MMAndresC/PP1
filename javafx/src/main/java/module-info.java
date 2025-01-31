module com.svalero.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.google.gson;

    opens com.svalero.javafx to javafx.fxml;
    exports com.svalero.javafx;
    exports com.svalero.javafx.tasks;
    opens com.svalero.javafx.tasks to javafx.fxml;
    exports com.svalero.javafx.controllers;
    opens com.svalero.javafx.controllers to javafx.fxml;
}