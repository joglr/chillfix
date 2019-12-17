package models;

public class SearchFilter implements Filter {
    private final String searchString;

    public SearchFilter(String searchString) {
        this.searchString = searchString.trim().toLowerCase();
    }

    @Override
    public boolean matches(Media m) {
        return m.getTitle().toLowerCase().contains((searchString)) ||
                m.getDescription().toLowerCase().contains((searchString)) ||
                Integer.toString(m.getYear()).toLowerCase().contains((searchString));
    }
}