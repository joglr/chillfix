package models;

public class TypeFilter implements Filter {
    private final Media.Type type;

    public TypeFilter(Media.Type type) {
        this.type = type;
    }

    public String toString() {
        return type.toString();
    }

    @Override
    public boolean matches(Media m) {
        return m.getType() == type;
    }
}
