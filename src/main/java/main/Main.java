package main;

import controllers.RootController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.HomeView;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        RootController.init(primaryStage, getClass());
        new HomeView();
    }

}
