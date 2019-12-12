package views;

import controllers.RootController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import models.Media;

import java.io.IOException;
import java.util.Arrays;

public class MediaDetailsView {
    private final Media media;

    public MediaDetailsView(Media media) {
        this.media = media;
        BorderPane root = new BorderPane();

        BorderPane container = new BorderPane();
        root.setCenter(container);
        VBox left = new VBox();
        VBox right = new VBox();
        HBox leftBottomButtons = new HBox();
        Button backButton = new Button("Tilbage");
        backButton.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            try {
                new MainView();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        //Tooltip.install(container, new Tooltip(media.getTitle()));
        Image poster = null;
        //container.setPrefHeight();
        container.setPrefWidth(600);

        MediaCard mediaCard = new MediaCard(media, false);
        Button AddToMyListButton = new Button("Tilføj til min liste");
        Button DeleteFromMyListButton = new Button("Fjern fra min liste");
        Region expandRegion = new Region();

        VBox.setVgrow(expandRegion, Priority.ALWAYS);

        Text title = new Text(media.getTitle().toUpperCase());
        Text description = new Text(media.getDescription());
        Text year = new Text("Årstal: " + media.getYear());
        Text rating = new Text("Rating: " + media.getRating());
        Text genre = new Text("Genre: " + String.join(", ", Arrays.asList(media.getGenres())));

        description.wrappingWidthProperty().setValue(400);

        container.setTop(backButton);
        container.setLeft(left);
        container.setMaxWidth(600);
        container.setRight(right);
        left.setSpacing(5);
        left.getChildren().addAll(title, description, year, rating, genre, expandRegion, leftBottomButtons);
        leftBottomButtons.getChildren().addAll(AddToMyListButton, DeleteFromMyListButton);
        right.getChildren().add(mediaCard.render());

        RootController.setCurrentRoot(root);
    }
}