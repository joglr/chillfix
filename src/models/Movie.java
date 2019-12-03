package models;

public class Movie extends Media implements Displayable {

    public Movie(String imdbID, String title, String description, int year, String[] genres, String posterFilePath) {
        super(imdbID, title, description, year, genres, posterFilePath);
    }

    @Override
    public void display() {
        // Til en start: Print filmen ud i System.out
        // På et senere tidspunkt: Vis filmen i vores UI.
    }
}
