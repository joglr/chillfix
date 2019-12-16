package models;

import java.util.Arrays;

public class GenreFilter implements Filter {

    private final String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean matches(Media m) {
        return !Arrays.asList(m.getGenres()).contains(genre);
    }
}
