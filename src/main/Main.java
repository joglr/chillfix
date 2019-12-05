package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/MainView.fxml"));
        primaryStage.setTitle("Chill Fix");
        primaryStage.setScene(new Scene(root, 1280/1.5, 720 / 1.5));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
