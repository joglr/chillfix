package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RootController {
    //    static Scene currentScene;
    private static Stage primaryStage;
    private static Scene globalScene;
    public static Class rootClass;

    public static void init(Stage primaryStage, Class rootClass) {
        //Anvender Singleton pattern
        RootController.rootClass = rootClass;
        RootController.primaryStage = primaryStage;
        primaryStage.setTitle("Chillfix");

        globalScene = new Scene(
                new AnchorPane()
                , 1280 / 1.5, 720 / 1.5);
        primaryStage.setScene(globalScene);
        //indlæsning af css fil
        globalScene.getStylesheets().add(rootClass.getResource("../styles/main.css").toExternalForm());
        primaryStage.show();
    }

    public static void setCurrentRoot(Parent root) {
        if (root == null) {
            //TODO: Introducer exception
            System.out.println("*** root cannot be null");
            return;
        }
        globalScene.setRoot(root);
        RootController.globalScene.setRoot(root);
        RootController.primaryStage.show();
    }

    public static Parent loadRoot(String path) throws IOException {
        return FXMLLoader.load(rootClass.getResource(path));
    }

}
