package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.CSVReader;
import models.Movie;
import models.Series;

import java.util.List;

public class Main extends Application {
    private List<Movie> movieList;
    private List<Series> seriesList;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        CSVReader movieData = new CSVReader("data/db/movies.csv");
        // Read all movies into MovieList
//        CSVReader seriesData = new CSVReader("data/db/series.csv");
        // Read all movies into SeriesList

        // Kald

//        for (Media)

    }


    public static void main(String[] args) {
        launch(args);
    }
}
