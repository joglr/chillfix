package models;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Media implements Displayable {

    private String imdbID;
   private String title;
   private String description;
   private int year;
    private int rating;
   private String[] genres;
   private String posterFilePath;


    public String getImdbID() {
        return imdbID;
    }

    public Media(String imdbID, String title, String description, int rating, int year, String[] genres, String posterFilePath) {
        this.imdbID = imdbID;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.year = year;
        this.genres = genres;
        this.posterFilePath = posterFilePath;
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

    public String getPosterFilePath() {
        return posterFilePath;
    }



    public Node render() {
        String movie = getTitle() + " (" + getYear() + ")";
        System.out.println(movie);
        Button myCoolButton = new Button(movie);

        return myCoolButton;
    }

    @Override
    public void display(Pane pane) {
        pane.getChildren().add(render());
    }

}
