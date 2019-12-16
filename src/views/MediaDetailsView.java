package views;

import controllers.Min_Liste_Controller;
import controllers.RootController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import models.Media;
import models.MyListFilter;

import java.io.IOException;
import java.util.Arrays;

class MediaDetailsView {

    public MediaDetailsView(Media media) {
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
        System.out.println(Min_Liste_Controller.getMy_list()
                .toString());
        if (!new MyListFilter().matches(media)) {
            Button DeleteFromMyListButton = new Button("Fjern fra min liste");
            leftBottomButtons.getChildren().addAll(DeleteFromMyListButton);
            DeleteFromMyListButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
                Min_Liste_Controller.getMy_list().remove(media.getImdbID());

                new MediaDetailsView(media);

            });

        } else {
            Button AddToMyListButton = new Button("Tilføj til min liste");
            AddToMyListButton.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
                Min_Liste_Controller.getMy_list().add(media.getImdbID());

                new MediaDetailsView(media);

            });
            leftBottomButtons.getChildren().addAll(AddToMyListButton);

        }
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
        right.getChildren().add(mediaCard.render());

        RootController.setCurrentRoot(root);
    }
}