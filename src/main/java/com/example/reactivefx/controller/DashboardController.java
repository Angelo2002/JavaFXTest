package com.example.reactivefx.controller;

import com.example.reactivefx.event.UserLoggedInEvent;
import com.example.reactivefx.event.service.EventBusService;
import com.example.reactivefx.state.AppState;
import javafx.animation.Interpolatable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {
    private final EventBusService eventBus;

    @FXML private Label welcomeLabel;

    public DashboardController(EventBusService eventBus) {
        this.eventBus = eventBus;
    }

    @FXML
    private void initialize() {
        eventBus.subscribe(UserLoggedInEvent.class, this::onUserLoggedIn);
        welcomeLabel.setText("Welcome!");
    }

    private void onUserLoggedIn(UserLoggedInEvent event) {

            welcomeLabel.setText("Welcome2, " + event.getUsername() + "!");
            System.out.println("User logged in2: " + event.getUsername());

    }
}