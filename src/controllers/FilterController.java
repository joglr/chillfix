package controllers;

import java.net.URL;
import java.util.ResourceBundle;

public class FilterController {

    private String currentFilteres;
    private String searchFilteres;
    private String genreFilteres;

    public FilterController(String currentFilteres, String searchFilteres, String genreFilteres) {
        this.currentFilteres = currentFilteres;
        this.genreFilteres = genreFilteres;
        this.searchFilteres = searchFilteres;
    }

}
