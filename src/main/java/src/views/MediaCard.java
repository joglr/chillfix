package main.java.src.views;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import main.java.src.models.Media;
import main.java.src.models.Renderable;


public class MediaCard implements Renderable {
    private Media media;
    private boolean isButtonVisible = true;

    public MediaCard(Media media) {
        this.media = media;
    }

    MediaCard(Media media, boolean isButtonVisible) {
        this(media);
        this.isButtonVisible = isButtonVisible;
    }

    public Node render() {
        HBox root = new HBox();
        root.getStyleClass().add("MediaCard");
        BorderPane container = new BorderPane();
        root.getChildren().add(container);

        container.setPrefHeight(200);
        container.setPrefWidth(150);

        BackgroundImage myBI = new BackgroundImage(new Image(media.getPosterFilePath()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(10.0, 10.0, true, true, true, true));
        container.setBackground(new Background(myBI));
        container.getStyleClass().add("MediaCardContainer");

        Button playButton = new Button("▶");
        playButton.getStyleClass().add("MediaPlayButton");
        container.setCenter(playButton);

        HBox actions = new HBox();

        if (isButtonVisible) {
            Tooltip.install(container, new Tooltip(media.getTitle()));
            Button detailsButton = new Button("Detaljer");
            playButton.getStyleClass().add("MediaCardButton");
            detailsButton.getStyleClass().add("MediaCardButton");
            detailsButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> new MediaDetailsView(media));
            Region expandRegion = new Region();
            HBox.setHgrow(expandRegion, Priority.ALWAYS);
            actions.getChildren().addAll(detailsButton, expandRegion);
        }

        container.setBottom(actions);

        return root;
    }
}
