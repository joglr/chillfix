package models;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Media implements Displayable {

    private String imdbID;
   private String title;
   private String description;
   private int year;
    private int rating1;
   private String[] genres;
   private String posterFilePath;


    public String getImdbID() {
        return imdbID;
    }

    public Media(String imdbID, String title, String description, int year, int rating1, String[] genres, String posterFilePath) {
        this.imdbID = imdbID;
        this.title = title;
        this.description = description;
        this.year = year;
        this.rating1 = rating1;
        this.genres = genres;
        this.posterFilePath = posterFilePath;
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


    @Override
    public void display(Pane pane) {
        String movie = getTitle() + " (" + getYear() + ")";
        System.out.println(movie);
        Button myCoolButton = new Button(movie);

        pane.getChildren().add(myCoolButton);
    }

}
