module com.example.myapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires org.apache.commons.io;
    requires mysql.connector.java;

    opens com.example.myapp to javafx.fxml;
    exports com.example.myapp;
    exports Interfaces;
    opens Interfaces to javafx.fxml;
}
