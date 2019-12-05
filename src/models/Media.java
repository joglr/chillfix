package models;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
        String movie = getTitle() + " (" + getYear() + ")";
        Image poster = null;
        Button myCoolButton = new Button("Tilføj til min liste");

        container.getChildren().add(myCoolButton);
        try {
            poster = new Image(this.getPosterFilePath());
        } catch (RuntimeException e) {
            System.out.println("*** IllegalArgumentException " + this.getPosterFilePath());
            poster = new Image("file:data/img/PlaceholderThumbnail.png");
        } finally {
            container.getChildren().add(new ImageView(poster));
        }

        return container;
    }

    @Override
    public void display(Pane pane) {
        pane.getChildren().add(render());
    }

}
