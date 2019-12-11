package models;

public class Series extends Media  {
    private Integer[] seasons;

    public Series(String imdbID, String title, String description, int rating, int year, String[] genres, String posterFilePath, Integer[] seasons) {
        super(imdbID, title, description, rating, year, genres, posterFilePath);
        this.seasons = seasons;
    }

    public Integer[] getSeasons() {
        return seasons;
    }

    @Override
    public Type getType() {
        return Media.Type.SERIES;
    }

    @Override
    public String getPosterFilePath() {
        return "file:data/img/series_thumbs/" + super.getPosterFileName();
    }
}
