package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import models.*;
import views.MediaCard;

import java.net.URL;
import java.text.Collator;
import java.util.*;
import java.util.stream.Stream;

public class HomeViewController implements Initializable {

    @FXML
    FlowPane container;

    @FXML
    TextField searchField;

    private List<Media> mediaList = new ArrayList<>();
    private Filter typeFilter;
    private Filter genreFilter;
    private Filter searchFilter;

    @FXML
    ToggleButton showAllButton;
    @FXML
    ToggleButton showMoviesButton;
    @FXML
    ToggleButton showSeriesButton;
    @FXML
    ChoiceBox genreChoiceBox;

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
//        for (String genre : uniqueGenres) {
//            int genreCount = 0;
//            for (Media m : mediaList) {
//                if (Arrays.asList(m.getGenres()).contains(genre)) genreCount++;
//            }
//            genresContainer.getChildren().add(new ToggleButton(genre.substring(0, 1).toUpperCase() + genre.substring(1) + " [" + genreCount + "]"));
//        }



        // Sort genres alphabetically
        Collection<String> genres =
                new TreeSet<>(Collator.getInstance());
//        genres.add("All");
        genres.addAll(uniqueGenres);
//        Stream.of(genres).map(str -> str.substring(0, 1).toUpperCase() + str.substring(1)).toArray(String[]::new)

//        ArrayList<String> sortedGenres = java.util.Collections.sort(genres, Collator.getInstance());

        genreChoiceBox.getItems().addAll(genres);
        resetGenreChoiceBox();
//        genreChoiceBox.getStyleClass().add(("test"));
//        genreChoiceBox.idProperty().set("test");
//        genreSelect.setStyle("-fx-font-size:  30px");


        // Capitalize
        // String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String[] parseGenres(String rowString) {
        return rowString.substring(1, rowString.length() - 1).split(",");
    }

    private void displayMedia() {
        System.out.println("Re-rendering");
        int renderCount = 0;
        container.getChildren().clear();
        for (Media media : mediaList) {
            if (renderCount > 50) break;
            if ((typeFilter != null && !typeFilter.matches(media)) ||
                    (genreFilter != null && !genreFilter.matches(media)) ||
                    (searchFilter != null && !searchFilter.matches(media))) continue;

            MediaCard mediaCard = new MediaCard(media);
            Node node = mediaCard.render();
            container.getChildren().add(node);
            renderCount++;
        }
        System.out.println("Rendered " + renderCount + " elements");
    }

    @FXML
    public void showAll() {
        typeFilter = null;
        resetTypeButtons();
        displayMedia();
    }

    @FXML
    public void showMovies() {
        typeFilter = new TypeFilter(Media.Type.MOVIE);

        deselectTypeButtons();
        showMoviesButton.selectedProperty().set(true);
        displayMedia();
    }

    @FXML
    public void showSeries() {
        typeFilter = new TypeFilter(Media.Type.SERIES);
        deselectTypeButtons();
        showSeriesButton.selectedProperty().set(true);
        displayMedia();
    }

    public void deselectTypeButtons() {
        ToggleButton[] toggleButtons = {showAllButton, showMoviesButton, showSeriesButton};
        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.selectedProperty().set(false);
        }
    }

    @FXML
    public void resetFilters() {
        typeFilter = null;
        genreFilter = null;
        searchFilter = null;
        resetTypeButtons();
        resetGenreChoiceBox();
        displayMedia();
    }

    private void resetGenreChoiceBox() {
        genreChoiceBox.setValue("all");
    }

    public void resetTypeButtons() {
        deselectTypeButtons();
        showAllButton.selectedProperty().set(true);
    }

    public void searchMedia() {
        String searchString = searchField.getText();
        searchFilter = searchString.length() == 0 ? null : new SearchFilter(searchString);
        displayMedia();
    }

    public void searchType() {
        if (searchField.getText().length() == 0) searchMedia();
    }
}
