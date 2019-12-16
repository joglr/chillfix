package main;

import controllers.RootController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.HomeView;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        RootController.init(primaryStage, getClass());
        new HomeView();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
