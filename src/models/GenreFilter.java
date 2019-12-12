package models;

import java.util.Arrays;

public class GenreFilter implements Filter {

    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public Boolean matches(Media m) {
        return Arrays.asList(m.getGenres()).contains(genre);
    }
}
