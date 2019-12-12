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
                new HomeView();
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
        title.setStyle("-fx-font-size: 25px");
        Text description = new Text(media.getDescription());
        description.setStyle("-fx-font-style: italic");
        description.wrappingWidthProperty().setValue(400);

        Text year = new Text("Årstal: " + media.getYear());
        Text rating = new Text("Anmeldelser: " + media.getRating() + "% kunne lide denne " + (media.getType() == Media.Type.SERIES ? "serie" : "film"));
        Text genre = new Text("Genre: " + String.join(", ", Arrays.asList(media.getGenres())));

        if (media.getType() == Media.Type.SERIES) {
            // TODO: List out seasons
        }

        container.setTop(backButton);
        container.setLeft(left);
        container.setMaxWidth(600);
        container.setMaxHeight(400);
        container.setRight(right);

        left.setSpacing(5);
        left.getChildren().addAll(title, description, year, rating, genre, expandRegion, leftBottomButtons);
        leftBottomButtons.getChildren().addAll(AddToMyListButton, DeleteFromMyListButton);
        right.getChildren().add(mediaCard.render());

        RootController.setCurrentRoot(root);
    }
}