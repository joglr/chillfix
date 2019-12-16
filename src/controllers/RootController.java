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
    private static Class rootClass;

    public static void init(Stage primaryStage, Class rootClass) {
        RootController.rootClass = rootClass;
        RootController.primaryStage = primaryStage;

        globalScene = new Scene(
                new AnchorPane()
                , 1280 / 1.5, 720 / 1.5);
        primaryStage.setScene(globalScene);
        globalScene.getStylesheets().add(rootClass.getResource("../styles/main.css").toExternalForm());
        primaryStage.show();
    }

    public static void setCurrentRoot(Parent root) {
        if (root == null) {
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

    public static Scene getCurrentScene() {
        return RootController.globalScene;
    }

}
