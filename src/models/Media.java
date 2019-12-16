package models;

public abstract class Media {
    private final String imdbID;
    private final String title;
    private final String description;
    private final int year;
    private final int rating;
    private final String[] genres;
    private final String posterFilePath;
    public enum Type { SERIES, MOVIE }

    Media(String imdbID, String title, String description, int rating, int year, String[] genres, String posterFilePath) {
        this.imdbID = imdbID;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.year = year;
        this.genres = genres;
        this.posterFilePath = posterFilePath;
    }

    public abstract Type getType();

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

    String getPosterFileName() {
        return posterFilePath;
    }

    public abstract String getPosterFilePath();
}