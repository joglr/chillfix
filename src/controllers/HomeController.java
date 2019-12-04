package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import models.CSVReader;
import models.Movie;
import models.Series;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HomeController {

    @FXML
    FlowPane container;
    private List<Movie> movieList;
    private List<Series> seriesList;

    public void initialize() {
        CSVReader movieData = new CSVReader("data/db/movies.csv");
        List<String[]> movies = movieData.getDataList();
        HashSet<String> uniquegenres = new HashSet<>();

        for (String[] row : movies) {
            String[] genres = row[5].substring(1, row[5].length() - 1).split(",");
            Movie m = new Movie(row[0], row[1], row[2], Integer.parseInt(row[4]), genres, row[6]);


            uniquegenres.addAll(Arrays.asList(genres));


            //m.display();
            Button myCoolButton = new Button(row[1]);

            container.getChildren().add(myCoolButton);


        }

        for (String genre : uniquegenres) {
            System.out.println(genre);
        }
    }
}
