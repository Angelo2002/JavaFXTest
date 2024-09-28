package com.example.reactivefx.event;

public class NavigationEvent {
    private final String targetView;

    public NavigationEvent(String targetView) {
        this.targetView = targetView;

    }

    public String getTargetView() {
        return targetView;
    }
}

