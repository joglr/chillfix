package models;

public class SearchFilter implements Filter {
    private final String searchString;

    public SearchFilter(String searchString) {
        //med metoden trim() trimmer man whitespaces
        this.searchString = searchString.trim().toLowerCase();
    }

    @Override
    public boolean matches(Media m) {
        //returnerer true hvis m i title, description eller year indeholder searchstring.
        //differentierer ikke mellem store og små bogstaver
        return m.getTitle().toLowerCase().contains((searchString)) ||
                m.getDescription().toLowerCase().contains((searchString)) ||
                Integer.toString(m.getYear()).toLowerCase().contains((searchString));
    }
}