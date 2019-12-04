package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.CSVReader;

import java.util.List;

public class HomeController {

    @FXML
    FlowPane container;

    public void initialize() {
        CSVReader movieData = new CSVReader("data/db/movies.csv");
        List<String[]> movies = movieData.getDataList();

        for(String[] movie : movies) {

            Button myCoolButton = new Button(movie[1]);

            container.getChildren().add(myCoolButton);
        }
    }
}
