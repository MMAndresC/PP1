module com.svalero.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.svalero.javafx to javafx.fxml;
    exports com.svalero.javafx;
}