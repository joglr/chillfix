package models;

import java.util.Arrays;

public class GenreFilter implements Filter {

    private final String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean matches(Media m) {
        //returnerer true hvis m indeholder den genre (defineret som felt) ud af flere mulighede genre (getGenres()).
        return Arrays.asList(m.getGenres()).contains(genre);
    }
}
