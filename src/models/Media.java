package models;

public class Media {

    private String imdbID;
   private String title;
   private String description;
   private int year;
   private String[] genres;
   private String posterFilePath;

    public String getImdbID() {
        return imdbID;
    }

    Media(String imdbID, String title, String description, int year, String[] genres, String posterFilePath) {
        this.imdbID = imdbID;
        this.title = title;
        this.description = description;
        this.year = year;
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
}
