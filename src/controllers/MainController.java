package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import models.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

public class MainController implements Initializable {

    @FXML
    FlowPane container;
    @FXML
    FlowPane genresContainer;
    private List<Media> mediaList = new ArrayList<Media>();
    private Filter typeFilter;
    private Filter genreFilter;
    private Filter searchFilter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CSVReader movieData = new CSVReader("data/db/movies.csv");
        CSVReader seriesData = new CSVReader("data/db/series.csv");

        List<String[]> movieRows = movieData.getDataList();
        List<String[]> seriesRows = seriesData.getDataList();

        HashSet<String> uniqueGenres = new HashSet<>();
        uniqueGenres.add("all");

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
        for (String genre : uniqueGenres) {
            int genreCount = 0;
            for (Media m : mediaList) {
                if (Arrays.asList(m.getGenres()).contains(genre)) genreCount++;
            }
            genresContainer.getChildren().add(new ToggleButton(genre.substring(0, 1).toUpperCase() + genre.substring(1) + " [" + genreCount + "]"));
        }
    }

    private String[] parseGenres(String rowString) {
        return rowString.substring(1, rowString.length() - 1).split(",");
    }

    public void displayMedia() {
        for (Media media : mediaList) {
            if (typeFilter != null && !typeFilter.matches(media)) return;
            if (genreFilter != null && !genreFilter.matches(media)) return;
            if (searchFilter != null && !searchFilter.matches(media)) return;
            Node node = media.render();
            container.getChildren().add(node);
        }
    }

    public void showAll(ActionEvent movieChosen) {
        typeFilter = null;
        displayMedia();
    }

    public void showMovies(ActionEvent movieChosen) {
        typeFilter = new TypeFilter(Media.Type.MOVIE);
        displayMedia();
    }

    public void showSeries(ActionEvent seriesChosen) {
        typeFilter = new TypeFilter(Media.Type.SERIES);
        displayMedia();
    }

    public void resetFilters(ActionEvent resetFilters) {
        typeFilter = null;
        genreFilter = null;
        searchFilter = null;
        displayMedia();
    }
}

