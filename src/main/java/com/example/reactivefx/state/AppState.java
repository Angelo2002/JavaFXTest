package com.example.reactivefx.state;

import java.util.HashMap;

public class AppState {
    private static AppState appState;
    private HashMap<String, Object> state = new HashMap<>();

    private AppState() {
    }

    public static AppState getInstance() {
        if (appState == null) {
            appState = new AppState();
        }
        return appState;
    }

    public void put(String key, Object value) {
        state.put(key, value);
    }

    public Object get(String key) {
        return state.get(key);
    }

    public void remove(String key) {
        state.remove(key);
    }

    public void clear() {
        state.clear();
    }

    public boolean containsKey(String key) {
        return state.containsKey(key);
    }


}
