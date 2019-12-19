package views;

import controllers.MyListController;
import controllers.RootController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Media;
import models.MyListFilter;
import models.Series;

import java.util.Arrays;

class MediaDetailsView {

    public MediaDetailsView(Media media) {
        BorderPane root = new BorderPane();
        root.getStyleClass().addAll("dark", "padding");
        BorderPane container = new BorderPane();
        container.getStyleClass().add("bordered");

        root.setCenter(container);

        ScrollPane leftWrapper = new ScrollPane();
        leftWrapper.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftWrapper.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        leftWrapper.getStyleClass().add("ScrollPane");

        VBox left = new VBox();
        leftWrapper.setContent(left);
        VBox right = new VBox();
        HBox bottomButtons = new HBox();
        bottomButtons.setSpacing(8);
        bottomButtons.setPadding(new Insets(8));

        // Knap 'Tilbage' og eventhandler til knappen, igennem en lambda expression
        Button backButton = new Button("Tilbage");
        bottomButtons.getChildren().add(backButton);
        backButton.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            new HomeView();
        });

        MediaCard mediaCard = new MediaCard(media, false);

        // Hvis media findes på 'MyList', så dannes en knap 'Fjern fra min liste' med en eventhandler, der fjerner mediet fra listen
        if (new MyListFilter().matches(media)) {
            Button DeleteFromMyListButton = new Button("Fjern fra min liste");
            bottomButtons.getChildren().addAll(DeleteFromMyListButton);
            DeleteFromMyListButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
                MyListController.getMyList().remove(media.getImdbID());

                new MediaDetailsView(media);

            });

        } else {
            // Hvis media ikke er en del af MyList, så er der mulighed for at tilføje til listen
            Button AddToMyListButton = new Button("Tilføj til min liste");
            AddToMyListButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
                MyListController.getMyList().add(media.getImdbID());
                new MediaDetailsView(media);

            });
            bottomButtons.getChildren().addAll(AddToMyListButton);

        }

        Text title = new Text(media.getTitle().toUpperCase());
        title.setStyle("-fx-font-size: 25px");
        title.getStyleClass().add("darkText");
        title.wrappingWidthProperty().setValue(540);

        Text description = new Text(media.getDescription());
        description.getStyleClass().add("darkText");
        description.setStyle("-fx-font-style: italic; -fx-font-size: 13");
        description.wrappingWidthProperty().setValue(550);

        Text year = new Text("Årstal: " + media.getYear());
        year.getStyleClass().add("darkText");

        Text rating = new Text("Anmeldelser: " + media.getRating() + "% kunne lide denne " + (media.getType() == Media.Type.SERIES ? "serie" : "film"));
        rating.getStyleClass().add("darkText");

        Text genre = new Text("Genre: " + String.join(", ", Arrays.asList(media.getGenres())));
        genre.getStyleClass().add("darkText");

        container.setMaxWidth(700);
        container.setPrefHeight(400);
        container.setMaxHeight(800);

        container.setLeft(leftWrapper);
        container.setRight(right);
        container.setBottom(bottomButtons);

        left.setSpacing(5);
        left.getChildren().addAll(title, description, year, rating, genre);
        Node mediaCardNode = mediaCard.render();
        mediaCardNode.getStyleClass().add("bordered");
        right.getChildren().add(mediaCardNode);

        if (media.getType() == Media.Type.SERIES) {
            Series series = (Series) media;
            int seasonCounter = 1;
            for (int i : series.getSeasons()) {
                VBox season = new VBox();
                FlowPane episodes = new FlowPane();
                season.setAlignment(Pos.CENTER_LEFT);
                Text seasonText = new Text("Sæson " + seasonCounter);
                seasonText.styleProperty().set("-fx-font-weight: bold");
                season.setSpacing(8);
                episodes.setHgap(8);
                episodes.setVgap(8);

                seasonText.getStyleClass().add("darkText");
                season.getChildren().add(seasonText);
                season.getChildren().add(episodes);

                for (int ii = 0; ii < i; ii++) {
                    int episodeNum = ii + 1;
                    // Tilføj et nul foran episoder der er mindre end 10
                    Button episodeButton = new Button("E" + (episodeNum < 10 ? "0" : "") + (episodeNum));
                    episodeButton.getStyleClass().add("DarkButton");
                    episodeButton.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
                        episodeButton.styleProperty().set("-fx-border-color: blue;");
                    });
                    episodes.getChildren().add(episodeButton);
                }
                left.getChildren().add(season);
                seasonCounter++;
            }
        }

        RootController.setCurrentRoot(root);
    }
}
