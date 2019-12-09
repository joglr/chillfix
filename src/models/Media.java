package models;

import views.MediaCard;

public abstract class Media {
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
}
