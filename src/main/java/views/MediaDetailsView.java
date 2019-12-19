package views;

import controllers.MyListController;
import controllers.RootController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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

        BorderPane container = new BorderPane();
        container.getStyleClass().add("bordered");
        root.setCenter(container);
        VBox left = new VBox();
        VBox right = new VBox();
        HBox leftBottomButtons = new HBox();

        //Knap 'Tilbage' og eventhandler til knappen, igennem en lambda expression
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

        //Hvis media findes på 'MyList', så dannes en knap 'Fjern fra min liste' med en eventhandler, der fjerner mediet fra listen
        if (new MyListFilter().matches(media)) {
            Button DeleteFromMyListButton = new Button("Fjern fra min liste");
            leftBottomButtons.getChildren().addAll(DeleteFromMyListButton);
            DeleteFromMyListButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
                MyListController.getMyList().remove(media.getImdbID());

                new MediaDetailsView(media);

            });

        } else {
            //Hvis media ikke er en del af MyList, så er der mulighed for at tilføje til listen
            Button AddToMyListButton = new Button("Tilføj til min liste");
            AddToMyListButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
                MyListController.getMyList().add(media.getImdbID());
                new MediaDetailsView(media);

            });
            leftBottomButtons.getChildren().addAll(AddToMyListButton);

        }
        Region expandRegion = new Region();

        VBox.setVgrow(expandRegion, Priority.ALWAYS);

        Text title = new Text(media.getTitle().toUpperCase());
        title.setStyle("-fx-font-size: 25px");
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
        container.setLeft(left);
        container.setMaxWidth(600);
        container.setMaxHeight(400);
        container.setRight(right);

        left.setSpacing(5);
        left.getChildren().addAll(title, description, year, rating, genre);
        right.getChildren().add(mediaCard.render());

        if (media.getType() == Media.Type.SERIES) {
            Series series = (Series) media;
            for (int i : series.getSeasons()) {
                FlowPane season = new FlowPane();
                HBox hbox = new HBox();
                for (int ii = 0; ii < i; ii++) {

                    Button episodeButton = new Button("E" + (ii + 1));
                    episodeButton.styleProperty().set("-fx-margin: 8px");
                    season.getChildren().add(episodeButton);
                }
                left.getChildren().add(season);
            }
        }

        left.getChildren().addAll(expandRegion, leftBottomButtons);

        RootController.setCurrentRoot(root);
    }
}
