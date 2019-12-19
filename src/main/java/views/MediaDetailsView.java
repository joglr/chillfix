package views;

import controllers.MyListController;
import controllers.RootController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import java.io.IOException;
import java.util.Arrays;

class MediaDetailsView {

    public MediaDetailsView(Media media) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("dark");
        root.getStyleClass().add("ScrollPane");

        ScrollPane wrapper = new ScrollPane();
        BorderPane container = new BorderPane();
        container.getStyleClass().add("bordered");

        wrapper.setContent(container);
        root.setCenter(wrapper);

        ScrollPane leftWrapper = new ScrollPane();
        leftWrapper.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftWrapper.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        leftWrapper.getStyleClass().add("ScrollPane");

        VBox left = new VBox();
        leftWrapper.setContent(left);
        VBox right = new VBox();
        HBox bottomButtons = new HBox();
        container.setBottom(bottomButtons);

        // Knap 'Tilbage' og eventhandler til knappen, igennem en lambda expression
        Button backButton = new Button("Tilbage");
        backButton.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
            try {
                new HomeView();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
        title.wrappingWidthProperty().setValue(400);

        Text description = new Text(media.getDescription());
        description.getStyleClass().add("darkText");
        description.setStyle("-fx-font-style: italic");
        description.wrappingWidthProperty().setValue(400);

        Text year = new Text("Årstal: " + media.getYear());
        year.getStyleClass().add("darkText");

        Text rating = new Text("Anmeldelser: " + media.getRating() + "% kunne lide denne " + (media.getType() == Media.Type.SERIES ? "serie" : "film"));
        rating.getStyleClass().add("darkText");

        Text genre = new Text("Genre: " + String.join(", ", Arrays.asList(media.getGenres())));
        genre.getStyleClass().add("darkText");

        container.setTop(backButton);
        container.setLeft(leftWrapper);
        container.setMaxWidth(800);
        container.setMaxHeight(400);
        container.setRight(right);

        left.setSpacing(5);
        left.getChildren().addAll(title, description, year, rating, genre);
        right.getChildren().add(mediaCard.render());

        if (media.getType() == Media.Type.SERIES) {
            Series series = (Series) media;
            int seasonCounter = 1;
            for (int i : series.getSeasons()) {
                HBox season = new HBox();
                FlowPane episodes = new FlowPane();
                episodes.getStyleClass().add("border");
                season.setAlignment(Pos.CENTER_LEFT);
                Text seasonText = new Text("Sæson " + seasonCounter + ":");
                seasonText.styleProperty().set("-fx-font-weight: bold");
                season.paddingProperty().set(new Insets(8));

                seasonText.getStyleClass().add("darkText");
                season.getChildren().add(seasonText);
                season.getChildren().add(episodes);
//                season.setFitToWidth(true);
//                episodes.setSpacing(8);

                for (int ii = 0; ii < i; ii++) {
                    Button episodeButton = new Button("" + (ii + 1));
                    episodeButton.styleProperty().set("-fx-margin: 8px");
                    episodeButton.addEventHandler(ActionEvent.ACTION, (ActionEvent e) -> {
                        episodeButton.styleProperty().set("-fx-background-color: blue;");
                    });
                    episodes.setMargin(episodeButton, new Insets(8));
                    episodes.getChildren().add(episodeButton);
                }
                left.getChildren().add(season);
                seasonCounter++;
            }
        }

//        left.getChildren().addAll(expandRegion);
        RootController.setCurrentRoot(root);
    }
}
