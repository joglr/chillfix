package main.java.src;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.src.controllers.RootController;
import main.java.src.views.HomeView;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        RootController.init(primaryStage, getClass());
        new HomeView();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
