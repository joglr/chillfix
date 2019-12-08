package models;

public class SearchFilter implements Filter {
    @Override
    public Boolean matches(Media m) {
        return true;
    }
}
