package com.example.reactivefx.controller;

import com.example.reactivefx.event.NavigationEvent;
import com.example.reactivefx.event.service.EventBusService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class NavigationController {
    private final Stage primaryStage;
    private final EventBusService eventBus;
    private final Map<String, LazyView> viewCache = new HashMap<>();
    private final Function<Class<?>, Object> controllerFactory;

    public NavigationController(Stage primaryStage, EventBusService eventBus, Function<Class<?>, Object> controllerFactory) {
        this.primaryStage = primaryStage;
        this.eventBus = eventBus;
        this.controllerFactory = controllerFactory;

        eventBus.subscribe(NavigationEvent.class, this::onNavigationEvent);
    }

    public void navigateTo(String viewName) {
        LazyView lazyView = viewCache.computeIfAbsent(viewName, this::createLazyView);
        Parent view = lazyView.getView();
        primaryStage.setScene(new Scene(view));
        lazyView.initializeController();
    }

    private LazyView createLazyView(String viewName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + viewName + ".fxml"));
            loader.setControllerFactory(controllerFactory::apply);
            Parent view = loader.load();
            Object controller = loader.getController();
            return new LazyView(view, controller);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load view: " + viewName, e);
        }
    }

    private void onNavigationEvent(NavigationEvent event) {
        navigateTo(event.getTargetView());
    }

    private static class LazyView {
        private final Parent view;
        private final Object controller;
        private boolean initialized = false;

        LazyView(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        Parent getView() {
            return view;
        }

        void initializeController() {
            if (!initialized && controller instanceof Initializable) {
                ((Initializable) controller).initialize();
                initialized = true;
            }
        }
    }

    public interface Initializable {
        void initialize();
    }
}