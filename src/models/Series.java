package models;

import javafx.scene.layout.Pane;

public class Series extends Media  implements Displayable {
    Integer[] seasons;

    public Series(String imdbID, String title, String description, int year, String[] genres, String posterFilePath, Integer[] seasons) {
        super(imdbID, title, description, year, genres, posterFilePath);
        this.seasons = seasons;
    }

    @Override
    public void display(Pane container) {

    }
}
