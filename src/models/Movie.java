package models;


public class Movie extends Media implements Displayable {

    public Movie(String imdbID, String title, String description, int rating1, int year, String[] genres, String posterFilePath) {
        super(imdbID, title, description, rating1, year, genres, posterFilePath);
    }

}