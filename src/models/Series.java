package models;

public class Series extends Media  {
    Integer[] seasons;

    public Series(String imdbID, String title, String description, int rating, int year, String[] genres, String posterFilePath, Integer[] seasons) {
        super(imdbID, title, description, rating, year, genres, posterFilePath);
        this.seasons = seasons;
    }
//            0: String id
//            1: String title
//            2: String description
//            3: Integer rating
//            4: Integer year
//            5: genres
//            6: poster
//            7: seasons

    public Integer[] getSeasons() {
        return seasons;
    }

    @Override
    MediaTypes getMediaType() {
        return MediaTypes.SERIES;
    }

    @Override
    public String getPosterFilePath() {
        return "file:data/img/series_thumbs/" + super.getPosterFileName();
    }
}
