package views;

import com.sun.javafx.scene.layout.region.BackgroundSizeConverter;
import javafx.event.ActionEvent;
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
        HBox root = new HBox();
        root.getStyleClass().add("MediaCard");
        BorderPane container = new BorderPane();
        root.getChildren().add(container);

        Image poster = null;
        container.setPrefHeight(200);
        container.setPrefWidth(150);

        try {
//            poster = new Image(media.getPosterFilePath());
        } catch (RuntimeException e) {
            System.out.println("*** IllegalArgumentException " + media.getPosterFilePath());
        } finally {
            BackgroundImage myBI = new BackgroundImage(new Image(media.getPosterFilePath()),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(10.0, 10.0, true, true, true, true));
            container.setBackground(new Background(myBI));
            container.getStyleClass().add("MediaCardContainer");
        }

        Button playButton = new Button("▶");
        playButton.getStyleClass().addAll("MediaPlayButton", "MediaCardButton");
        container.setCenter(playButton);

        if (isButtonVisible) {
            Tooltip.install(container, new Tooltip(media.getTitle()));
//            Button myCoolButton = new Button("Gem");
            Button detailsButton = new Button("Detaljer");
            Button saveButton = new Button("⭐");
            HBox actions = new HBox();
            detailsButton.getStyleClass().add("MediaCardButton");
            saveButton.getStyleClass().add("MediaCardButton");

            detailsButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
                new MediaDetailsView(media);
            });
            Region expandRegion = new Region();
            HBox.setHgrow(expandRegion, Priority.ALWAYS);
            actions.getChildren().addAll(detailsButton, expandRegion, saveButton);


            container.setBottom(actions);
        }
        return root;
    }
}