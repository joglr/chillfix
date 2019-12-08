package models;

public class GenreFilter implements Filter {
    @Override
    public Boolean matches(Media m) {
        return true;
    }
}
