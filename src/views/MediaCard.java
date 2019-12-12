package views;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import models.Media;

public class MediaCard {
    private final Media media;
    private boolean isButtonVisible = true;

    public MediaCard(Media media) {
        this.media = media;
    }

    MediaCard(Media media, boolean isButtonVisible) {
        this(media);
        this.isButtonVisible = isButtonVisible;
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

        if (isButtonVisible) {
            Button myCoolButton = new Button("Gem");
            Region expandRegion = new Region();
            VBox.setVgrow(expandRegion, Priority.ALWAYS);
            container.getChildren().add(expandRegion);

            container.getChildren().add(myCoolButton);
        }
        return container;
    }
}