package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.NoSuchFileException;
import models.NullRootException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RootController {
    public static Class rootClass;
    //    static Scene currentScene;
    private static Stage primaryStage;
    private static Scene globalScene;

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
        globalScene.getStylesheets().add(rootClass.getClassLoader().getResource("styles/main.css").toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void setCurrentRoot(Parent root) throws NullRootException {
        if (root == null) {
            throw new NullRootException("*** root cannot be null");
        }
        globalScene.setRoot(root);
        RootController.globalScene.setRoot(root);
        RootController.primaryStage.show();
    }

    public static Parent loadRoot(String path) throws IOException {
        return FXMLLoader.load(RootController.getURL(path));
    }

    public static URL getURL(String path) {
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }

    public static InputStream getURLAsStream(String path) throws NoSuchFileException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (inputStream == null) throw new NoSuchFileException(path);
        return inputStream;
    }

}
