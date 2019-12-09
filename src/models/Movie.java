package models;


public class Movie extends Media {

    public Movie(String imdbID, String title, String description, int rating, int year, String[] genres, String posterFilePath) {
        super(imdbID, title, description, rating, year, genres, posterFilePath);
    }

    @Override
    Type getType() {
        return Type.MOVIE;
    }

    @Override
    public String getPosterFilePath() {
        return "file:data/img/movie_thumbs/" + super.getPosterFileName();
    }
}