package views;

import controllers.RootController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import models.Media;

import java.io.IOException;

public class MediaCard {
    private final Media media;

    public MediaCard(Media media) {
        this.media = media;
    }

    public Node render() {
        VBox container = new VBox();
        Tooltip.install(container, new Tooltip(media.getTitle()));
        Image poster = null;
        container.setPrefHeight(200);
        container.setPrefWidth(150);

        try {
            poster = new Image(media.getPosterFilePath());
        } catch (RuntimeException e) {
            System.out.println("*** IllegalArgumentException " + media.getPosterFilePath());
        } finally {
            ImageView imageView = new ImageView(poster);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(150);

            BackgroundImage myBI = new BackgroundImage(new Image(media.getPosterFilePath()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            container.setBackground(new Background(myBI));
            container.setId("#card");
        }

        Button myCoolButton = new Button("Gem");
        Region expandRegion = new Region();
        myCoolButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../views/mediaViewTest.fxml"));
                RootController.setCurrentRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        VBox.setVgrow(expandRegion, Priority.ALWAYS);
        container.getChildren().add(expandRegion);
        container.getChildren().add(myCoolButton);

        myCoolButton.getStyleClass().add("test");

        return container;
    }
}