package views;

import controllers.RootController;
import javafx.event.ActionEvent;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import models.Media;
import models.NoSuchFileException;
import models.Renderable;

import java.io.InputStream;


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
        container.setPrefHeight(200);
        container.setPrefWidth(150);

        InputStream inputStream = null;
        boolean hasImage = false;

        try {
            inputStream = (RootController.getURLAsStream(media.getPosterFilePath()));
        } catch (NoSuchFileException e) {
            try {
                inputStream = RootController.getURLAsStream("data/img/PlaceholderThumbnail.png");
                hasImage = true;
            } catch (NoSuchFileException e2) {
                Text imagePlaceholderError = new Text("Error: Could not load poster placeholder");
                imagePlaceholderError.getStyleClass().add("darkText");
                imagePlaceholderError.wrappingWidthProperty().set(100);
                container.setTop(imagePlaceholderError);
            }
        } finally {
            if (inputStream != null) {
                Image image = new Image(inputStream);
                BackgroundImage myBI = new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        new BackgroundSize(10.0, 10.0, true, true, true, true));

                container.setBackground(new Background(myBI));
            }
        }

        container.getStyleClass().add("MediaCardContainer");

        Button playButton = new Button("▶");
        playButton.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            playButton.styleProperty().set("-fx-background-color: blue;");
        });
        playButton.getStyleClass().add("MediaPlayButton");
        container.setCenter(playButton);

        HBox actions = new HBox();

        if (isButtonVisible) {
            playButton.getStyleClass().add("MediaCardButton");

            Button detailsButton = new Button("Detaljer");
            detailsButton.getStyleClass().add("MediaCardButton");
            detailsButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> new MediaDetailsView(media));
            Region expandRegion = new Region();
            HBox.setHgrow(expandRegion, Priority.ALWAYS);
            actions.getChildren().addAll(expandRegion, detailsButton);

            Text mediaTitle = new Text(media.getTitle());
            mediaTitle.setWrappingWidth(150);
            mediaTitle.wrappingWidthProperty().set(150);
            mediaTitle.getStyleClass().add(!hasImage ? "MediaCardTitle" : "AlwaysTitle");
            mediaTitle.setCache(true);
            mediaTitle.setCacheHint(CacheHint.SPEED);
            container.setTop(mediaTitle);
        }

        container.setBottom(actions);

        return root;
    }
}
