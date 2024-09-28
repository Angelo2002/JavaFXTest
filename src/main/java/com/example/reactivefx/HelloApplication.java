package com.example.reactivefx;

import com.example.reactivefx.controller.DashboardController;
import com.example.reactivefx.controller.LoginController;
import com.example.reactivefx.controller.NavigationController;
import com.example.reactivefx.event.service.EventBusService;
import com.example.reactivefx.service.UserService;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private EventBusService eventBus;
    private NavigationController navigationController;
    private UserService userService;

    @Override
    public void start(Stage primaryStage) {
        // Initialize core services
        eventBus = new EventBusService();
        userService = new UserService(eventBus);
        navigationController = new NavigationController(primaryStage, eventBus, this::createController);

        // Navigate to initial view
        navigationController.navigateTo("LoginView");

        primaryStage.setTitle("Event Bus Demo");
        primaryStage.show();
    }

    private Object createController(Class<?> controllerClass) {
        if (controllerClass == LoginController.class) {
            return new LoginController(eventBus, userService);
        } else if (controllerClass == DashboardController.class) {
            return new DashboardController(eventBus);
        }
        throw new IllegalArgumentException("Unknown controller class: " + controllerClass.getName());
    }

    public static void main(String[] args) {
        launch(args);
    }
}