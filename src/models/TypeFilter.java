package models;

public class TypeFilter implements Filter {
    @Override
    private Media type;

    public TypeFilter(Media type) {
        this.type = type;
    }

    public Media getType() {
        return type;
    }

    public Boolean matches(Media m) {
        return m instanceof;
    }
}
