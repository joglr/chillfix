package models;

import static javax.swing.JOptionPane.showMessageDialog;


public class ErrorHandler {
    private String message;

    public ErrorHandler(String message) {
        this.message = message;
    }

    public void show() {
        showMessageDialog(null, message);
        System.exit(0);
    }
}
