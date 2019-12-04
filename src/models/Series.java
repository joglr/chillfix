package models;

import javafx.scene.layout.Pane;

public class Series extends Media  implements Displayable {
    public Series(String imdbID, String title, String description, int year, String[] genres, String posterFilePath) {
        super(imdbID, title, description, year, genres, posterFilePath);
    }

    @Override
    public void display(Pane container) {

    }
}
