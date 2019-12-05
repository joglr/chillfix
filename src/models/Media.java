package models;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class Media implements Displayable {

    private String imdbID;
    private String title;
    private String description;
    private int year;
    private int rating;
    private String[] genres;
    private String posterFilePath;


    public Media(String imdbID, String title, String description, int rating, int year, String[] genres, String posterFilePath) {
        this.imdbID = imdbID;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.year = year;
        this.genres = genres;
        this.posterFilePath = posterFilePath;
    }

    public String getImdbID() {
        return imdbID;
    }
    public int getRating() {
        return rating;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getYear() {
        return year;
    }
    public String[] getGenres() {
        return genres;
    }
    public String getPosterFileName() {
        return posterFilePath;
    }

    public abstract String getPosterFilePath();

    public Node render() {
        VBox container = new VBox();
//        String movie = getTitle() + " (" + getYear() + ")";
//        Text title = new Text(getTitle());
        Tooltip.install(container, new Tooltip(getTitle()));

//        title.setStyle("-fx-text-overrun: false;");
//        container.getChildren().add(title);
        Image poster = null;

        container.setPrefHeight(200);


        container.setPrefWidth(150);
        try {
            poster = new Image(this.getPosterFilePath());
        } catch (RuntimeException e) {
            System.out.println("*** IllegalArgumentException " + this.getPosterFilePath());
//            poster = new Image("file:data/img/PlaceholderThumbnail.png");

        } finally {
            ImageView imageView = new ImageView(poster);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(150);

            BackgroundImage myBI= new BackgroundImage(new Image(this.getPosterFilePath()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
//then you set to your node
            container.setBackground(new Background(myBI));
//        container.setStyle("-fx-background-image: url(\"" + this.getPosterFilePath() +"\");");
//            container.getChildren().add(imageView);
            container.setId("#card");
        }

        Button myCoolButton = new Button("Gem");
        Region expandRegion = new Region();
        VBox.setVgrow(expandRegion, Priority.ALWAYS);
        container.getChildren().add(expandRegion);
        container.getChildren().add(myCoolButton);

        return container;
    }

    @Override
    public void display(Pane pane) {
        pane.getChildren().add(render());
    }

}
