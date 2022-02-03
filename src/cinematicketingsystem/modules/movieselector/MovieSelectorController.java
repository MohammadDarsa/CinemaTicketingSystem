package cinematicketingsystem.modules.movieselector;

import cinematicketingsystem.models.movie.Movie;
import cinematicketingsystem.modules.moviecard.MovieCardController;
import cinematicketingsystem.modules.sidenav.SideNavController;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MovieSelectorController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private HBox homeBtn;

    @FXML
    private HBox moviesBtn;

    @FXML
    private HBox ticketsBtn;

    @FXML
    private HBox logoutBtn;

    @FXML
    private HBox topNav;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;


    private DBManager dbManager;
    private SceneManager sceneManager;

    private List<Movie> movieList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbManager = DBManager.getInstance();
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
        AnchorPane anchorPane = fxmlLoader.load();
        MovieCardController movieCardController = fxmlLoader.getController();
        movieCardController.setData(movie);
        flowPane.getChildren().add(anchorPane);
    }


}
