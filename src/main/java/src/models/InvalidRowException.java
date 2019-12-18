package main.java.src.models;

public class InvalidRowException extends Exception {
    protected String[] row;

    public InvalidRowException() {
        super("Ingen resultater, prøv at søge med titel");
        this.row = row;

    }

}
