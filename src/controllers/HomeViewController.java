package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import models.*;
import views.MediaCard;

import java.net.URL;
import java.text.Collator;
import java.util.*;
import java.util.stream.Stream;

public class HomeViewController implements Initializable {

    @FXML
    FlowPane mediaContainer;

    @FXML
    TextField searchField;

    private final List<Media> mediaList = new ArrayList<>();
    private Filter typeFilter;
    private Filter genreFilter;
    private Filter searchFilter;
    @FXML
    Button showMyList;
    private Filter myListFilter;
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

        // TODO: checked exceptions, om rows er tilpas længde.
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

        // Sort genres alphabetically
        Collection<String> genres = new TreeSet<>(Collator.getInstance());
        genres.addAll(uniqueGenres);
        genreChoiceBox.getItems().addAll(genres);
        resetGenreChoiceBox();
        displayMedia();
    }

    private String[] parseGenres(String rowString) {
        return rowString.substring(1, rowString.length() - 1).split(",");
    }

    /**
     * (Re)renders filtered media items from mediaList into mediaContainer
     */
    private void displayMedia() {
        int renderCount = 0;
        mediaContainer.getChildren().clear();
        for (Media media : mediaList) {
            if (renderCount > 50) break;
            if ((typeFilter != null && !typeFilter.matches(media)) ||
                    (genreFilter != null && !genreFilter.matches(media)) ||
                    (searchFilter != null && !searchFilter.matches(media)) ||
                    (myListFilter != null && !myListFilter.matches(media))) continue;

            MediaCard mediaCard = new MediaCard(media);
            Node node = mediaCard.render();
            mediaContainer.getChildren().add(node);
            renderCount++;
        }
        if (renderCount == 0) {
            Label text = new Label("Ingen resultater");
            text.getStyleClass().add("darkText");
            mediaContainer.getChildren().add(text);
        }
        System.out.println("Rendered " + renderCount + " elements");
    }

    /**
     * The following three methods follows the following specification
     * void showType<type>()
     * The methods
     * 1) updates the typeFiler to the appropriate filter
     * 2) unselects all typeButtons
     * 3) selects the selected typeButton
     * 4) calls `displayMedia()` to rerender mediaContainer with the active filters
     */
    @FXML
    public void showTypeAll() {
        typeFilter = null;
        resetTypeButtons();
        displayMedia();
    }

    @FXML
    public void showTypeMovies() {
        typeFilter = new TypeFilter(Media.Type.MOVIE);

        deselectTypeButtons();
        showMoviesButton.selectedProperty().set(true);
        displayMedia();
    }

    @FXML
    public void showTypeSeries() {
        typeFilter = new TypeFilter(Media.Type.SERIES);
        deselectTypeButtons();
        showSeriesButton.selectedProperty().set(true);
        displayMedia();
    }

    /**
     * Deselects typeButtons
     */
    private void deselectTypeButtons() {
        ToggleButton[] toggleButtons = {showAllButton, showMoviesButton, showSeriesButton};
        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.selectedProperty().set(false);
        }
    }

    /**
     * Resets all filters and buttons, then calls displayMedia()
     */
    @FXML
    private void resetFilters() {
        typeFilter = null;
        genreFilter = null;
        searchFilter = null;
        myListFilter = null;
        resetTypeButtons();
        resetGenreChoiceBox();
        showMyList.setText("Min Liste");
        showMyList.setOnAction((ActionEvent e) -> showMyList());
        displayMedia();
    }

    private void resetGenreChoiceBox() {
        genreChoiceBox.setValue("all");
    }

    private void resetTypeButtons() {
        deselectTypeButtons();
        showAllButton.selectedProperty().set(true);
    }

    /* onAction handlers */

    @FXML
    private void searchFieldSubmitHandler() {
        String searchString = searchField.getText();
        searchFilter = searchString.length() == 0 ? null : new SearchFilter(searchString);
        displayMedia();
    }

    @FXML
    public void searchFieldKeyTypedHandler() {
        if (searchField.getText().length() == 0) searchFieldSubmitHandler();
    }

    public void genreChoiceBoxActionHandler() {
        String value = genreChoiceBox.getValue().toString();
        genreFilter = value.equals("all") ? null : new GenreFilter(value);
        displayMedia();
    }

    @FXML
    private void showMyList() {
        myListFilter = new MyListFilter();
        showMyList.setText("Tilbage");
        showMyList.setOnAction((ActionEvent e) -> resetFilters());
        displayMedia();
        System.out.println(Min_Liste_Controller.getMy_list());
    }
}
