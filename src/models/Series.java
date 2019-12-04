package models;

import javafx.scene.layout.Pane;

public class Series extends Media  implements Displayable {
    Integer[] seasons;

    public Series(String imdbID, String title, int rating, int year, String description, String[] genres, String posterFilePath, Integer[] seasons) {
        super(imdbID, title, description, rating, year, genres, posterFilePath);
        this.seasons = seasons;
    }

    public Integer[] getSeasons() {
        return seasons;
    }

    @Override
    public void display(Pane container) {

    }
}
