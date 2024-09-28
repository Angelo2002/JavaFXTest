package com.example.reactivefx.event;

public class LoginFailedEvent {
    private final String errorMessage;

    public LoginFailedEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
