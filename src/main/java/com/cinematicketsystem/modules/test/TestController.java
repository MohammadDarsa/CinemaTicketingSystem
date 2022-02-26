package com.cinematicketsystem.modules.test;

import com.cinematicketsystem.models.movie.Movie;
import com.cinematicketsystem.modules.helloworld.HelloWorldController;
import com.cinematicketsystem.modules.moviecard.MovieCardController;
import com.cinematicketsystem.utils.DBManager;
import com.cinematicketsystem.utils.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TestController implements Initializable {

    @FXML
    private HelloWorldController mainNav;

    @FXML
    private ScrollPane scrollPane;
    private FlowPane flow;
    private GridPane gridPane;

    private DBManager dbManager;
    private UserManager userManager;
    private int row = 0;
    private int column = 0;

    private List<Movie> movieList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flow = new FlowPane();
        gridPane = new GridPane();
        scrollPane.setContent(gridPane);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        dbManager = DBManager.getInstance();
        movieList = dbManager.selectAll(Movie.class);
        movieList = movieList.stream().peek(this::addMovie).collect(Collectors.toList());

    }

    @SneakyThrows
    private void addMovie(Movie movie) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/movieCard.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        MovieCardController movieCardController = fxmlLoader.getController();
        movieCardController.setData(movie);
        gridPane.add(anchorPane, column++, row);
        if(column >= 3) {
            row++;
            column%=3;
        }
    }
}
