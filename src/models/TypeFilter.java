package models;

public class TypeFilter implements Filter {
    private Media.Type type;

    public TypeFilter(Media.Type type) {
        this.type = type;
    }

    @Override
    public Boolean matches(Media m) {
        return m.getType() == type;
    }
}
