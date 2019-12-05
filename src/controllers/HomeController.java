package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import models.CSVReader;
import models.Media;
import models.Movie;
import models.Series;

import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

public class HomeController implements Initializable {

    @FXML
    FlowPane container;
    private List<Media> mediaList = new ArrayList<Media>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CSVReader movieData = new CSVReader("data/db/movies.csv");
        CSVReader seriesData = new CSVReader("data/db/series.csv");

        List<String[]> movieRows = movieData.getDataList();
        List<String[]> seriesRows = seriesData.getDataList();

        HashSet<String> uniqueGenres = new HashSet<>();

        for (String[] row : movieRows) {
            String[] genres = parseGenres(row[5]);
            Media movie = new Movie(
                    row[0], row[1], row[2],
                    Integer.parseInt(row[3]), Integer.parseInt(row[4]),
                    genres,
                    row[6]);
            mediaList.add(movie);
            uniqueGenres.addAll(Arrays.asList(genres));
        }

        for (String[] row : seriesRows) {
            String[] genres = parseGenres(row[5]);
            String[] stringNumbers = row[7].substring(1, row[7].length() - 1).split(",");
            Integer[] seasons = Stream.of(stringNumbers).map(Integer::valueOf).toArray(Integer[]::new);

            Media series = new Series(row[0], row[1], row[2], Integer.parseInt(row[3]), Integer.parseInt(row[4]), genres, row[6], seasons);
            mediaList.add(series);
            uniqueGenres.addAll(Arrays.asList(genres));
        }
        displayMedia();
    }

    private String[] parseGenres(String rowString) {
        return rowString.substring(1, rowString.length() - 1).split(",");
    }

    public void displayMedia() {
        for (Media media : mediaList) {
            Node node = media.render();
            container.getChildren().add(node);
        }
    }
}

