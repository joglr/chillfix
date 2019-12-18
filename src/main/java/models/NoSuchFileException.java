package models;

public class NoSuchFileException extends NullPointerException {
    private String path;

    public NoSuchFileException(String path) {
        super("*** file not found");
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
