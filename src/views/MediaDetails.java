package views;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Media;

import java.util.Arrays;

public class MediaDetails {
    private final Media media;

    public MediaDetails(Media media) {
        this.media = media;
    }

    public Node render() {
        HBox container = new HBox();
        VBox left = new VBox();
        VBox right = new VBox();
        HBox leftButtons = new HBox();
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

        container.getChildren().add(left);
        container.getChildren().add(right);
        left.getChildren().add(title);
        left.setSpacing(5);
        left.getChildren().add(description);
        left.getChildren().add(year);
        left.getChildren().add(rating);
        left.getChildren().add(genre);
        left.getChildren().add(expandRegion);
        left.getChildren().add(leftButtons);
        leftButtons.getChildren().add(AddToMyListButton);
        leftButtons.getChildren().add(DeleteFromMyListButton);
        right.getChildren().add(mediaCard.render());


        return container;
    }
}