package com.example.reactivefx.controller;

import com.example.reactivefx.service.UserService;
import com.example.reactivefx.event.LoginFailedEvent;
import com.example.reactivefx.event.service.EventBusService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private final EventBusService eventBus;
    private final UserService userService;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    public LoginController(EventBusService eventBus, UserService userService) {
        this.eventBus = eventBus;
        this.userService = userService;

        eventBus.subscribe(LoginFailedEvent.class, this::onLoginFailed);

    }

    @FXML
    private void initialize() {
        // Any initialization logic
    }

    @FXML
    private void onLoginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        userService.login(username, password);
    }

    private void onLoginFailed(LoginFailedEvent event) {
        // Show error message to the user
        System.out.println("Login failed: " + event.getErrorMessage());
    }
}
