package models;

import javafx.scene.layout.Pane;

public class Series extends Media  implements Displayable {
    Integer[] seasons;

    public Series(String imdbID, String title, int year, int rating1, String description, String[] genres, String posterFilePath, Integer[] seasons) {
        super(imdbID, title, description, year, rating1, genres, posterFilePath);
        this.seasons = seasons;
    }

    @Override
    public void display(Pane container) {

    }
}
