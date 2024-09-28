package com.example.reactivefx.service;

import com.example.reactivefx.event.LoginFailedEvent;
import com.example.reactivefx.event.NavigationEvent;
import com.example.reactivefx.event.UserLoggedInEvent;
import com.example.reactivefx.event.service.EventBusService;
import com.example.reactivefx.state.AppState;

public class UserService {
    private final EventBusService eventBus;

    public UserService(EventBusService eventBus) {
        this.eventBus = eventBus;
    }

    public void login(String username, String password) {
        // Simulate login logic
        boolean loginSuccessful = "admin".equals(username) && "password".equals(password);

        if (loginSuccessful) {
            eventBus.publish(new NavigationEvent("DashboardView"));
            eventBus.publish(new UserLoggedInEvent(username));
            AppState.getInstance().put("username", username);
        } else {
            eventBus.publish(new LoginFailedEvent("Invalid username or password"));
        }
    }
}