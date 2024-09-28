module com.example.reactivefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql; // Added required module

    // Add other required modules
    opens com.example.reactivefx to javafx.fxml;
    exports com.example.reactivefx;
    opens com.example.reactivefx.event to javafx.fxml;
    exports com.example.reactivefx.event;
    opens com.example.reactivefx.event.service to javafx.fxml;
    exports com.example.reactivefx.event.service;
    opens com.example.reactivefx.controller to javafx.fxml;
    exports com.example.reactivefx.controller;
    exports com.example.reactivefx.service;
    opens com.example.reactivefx.service to javafx.fxml;

}