package main.java.src.views;

import main.java.src.controllers.RootController;

import java.io.IOException;

public class HomeView {
    public HomeView() throws IOException {
        RootController.setCurrentRoot(RootController.loadRoot("/main/java/src/views/HomeView.fxml"));
    }
}
