package com.cinematicketsystem.modules.movieselector;

import com.cinematicketsystem.models.movie.Movie;
import com.cinematicketsystem.modules.moviecard.MovieCardController;
import com.cinematicketsystem.modules.movieselector.sortstrat.NameSort;
import com.cinematicketsystem.modules.movieselector.sortstrat.PriceSort;
import com.cinematicketsystem.modules.movieselector.sortstrat.SortMovieService;
import com.cinematicketsystem.utils.DBManager;
import com.cinematicketsystem.utils.SceneManager;
import com.cinematicketsystem.utils.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MovieSelectorController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private HBox topNav;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;


    private DBManager dbManager;
    private SceneManager sceneManager;
    private UserManager userManager;

    private List<Movie> movieList;
    private SortMovieService sortMovieService;

    @FXML
    public void priceSort(ActionEvent event) {
        sortMovieService.setMovieSort(new PriceSort());
        sortMovieService.sort(movieList);
        fillFlow();
    }

    @FXML
    public void nameSort(ActionEvent event) {
        sortMovieService.setMovieSort(new NameSort());
        sortMovieService.sort(movieList);
        fillFlow();
    }

    private void fillFlow() {
        flowPane.getChildren().remove(0, flowPane.getChildren().size());
        movieList = movieList.stream().peek(this::addMovie).collect(Collectors.toList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flowPane.setFocusTraversable(true);
        sortMovieService = new SortMovieService();
        dbManager = DBManager.getInstance();
        userManager = UserManager.getInstance();
        movieList = dbManager.selectAll(Movie.class);
        movieList = movieList.stream().peek(this::addMovie).collect(Collectors.toList());
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    @SneakyThrows
    private void addMovie(Movie movie) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/movieCard.fxml"));
        VBox anchorPane = fxmlLoader.load();
        MovieCardController movieCardController = fxmlLoader.getController();
        movieCardController.setData(movie);
        flowPane.getChildren().add(anchorPane);
    }
}