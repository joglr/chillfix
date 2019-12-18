package main.java.src.models;


public class Movie extends Media {

    public Movie(String imdbID, String title, String description, int rating, int year, String[] genres, String posterFilePath) {
        super(imdbID, title, description, rating, year, genres, posterFilePath);
    }

    @Override
    public Type getType() {
        //typen er et enum fra superklassen Media, hvor der er MOVIE og SERIES
        return Media.Type.MOVIE;
    }

    @Override
    public String getPosterFilePath() {
        return "data/img/movie_posters/" + super.getPosterFileName();
    }
}