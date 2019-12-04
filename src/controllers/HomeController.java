package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import models.CSVReader;
import models.Media;
import models.Movie;
import models.Series;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class HomeController {

    @FXML
    FlowPane container;
    private List<Media> mediaList;

    public void initialize() {
        CSVReader movieData = new CSVReader("data/db/movies.csv");
        CSVReader seriesData = new CSVReader("data/db/series.csv");

        List<String[]> movieRows = movieData.getDataList();
        List<String[]> seriesRows = seriesData.getDataList();
//                new ArrayList<>();

//        rows.addAll(movieData.getDataList());
//        rows.addAll(seriesData.getDataList());

        HashSet<String> uniqueGenres = new HashSet<>();

        for (String[] row : movieRows) {
            String[] genres = row[5].substring(1, row[5].length() - 1).split(",");
            Media movie = new Movie(row[0], row[1], row[2], Integer.parseInt(row[3]), Integer.parseInt(row[4]), genres, row[6]);
            mediaList.add(movie);
            uniqueGenres.addAll(Arrays.asList(genres));

        }

        for (String[] row : seriesRows) {
            String[] genres = row[5].substring(1, row[5].length() - 1).split(",");
            String[] stringNumbers = row[7].substring(1, row[7].length() - 1).split(",");
            Integer[] seasons = Stream.of(stringNumbers).map(Integer::valueOf).toArray(Integer[]::new);

            Media series = new Series(row[0], row[1], Integer.parseInt(row[2]), Integer.parseInt(row[3]), row[4],  genres, row[6], seasons);
            mediaList.add(series);
            uniqueGenres.addAll(Arrays.asList(genres));

        }
        displayMedia();

        for (String genre : uniqueGenres) {
            System.out.println(genre);
        }
    }

    public void displayMedia() {
        for (Media media : mediaList) {
            media.display(container);
        }
    }
}

