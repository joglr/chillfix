package models;

public class SearchFilter implements Filter {
    private String searchString;

    public SearchFilter(String searchString) {
        this.searchString = searchString.trim().toLowerCase();
    }

    @Override
    public Boolean matches(Media m) {
        return m.getTitle().toLowerCase().contains((searchString)) ||
                m.getDescription().toLowerCase().contains((searchString)) ||
                Integer.toString(m.getYear()).toLowerCase().contains((searchString));
    }
}