package cinematicketingsystem.modules.movieselector;

import cinematicketingsystem.models.movie.Movie;
import cinematicketingsystem.modules.moviecard.MovieCardController;
import cinematicketingsystem.modules.movieselector.sortstrat.NameSort;
import cinematicketingsystem.modules.movieselector.sortstrat.PriceSort;
import cinematicketingsystem.modules.movieselector.sortstrat.SortMovieService;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;
import cinematicketingsystem.utils.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
//        VBox anchorPane = createMovieCard(movie);
        VBox anchorPane = fxmlLoader.load();
        MovieCardController movieCardController = fxmlLoader.getController();
        movieCardController.setData(movie);
        flowPane.getChildren().add(anchorPane);
    }

    private VBox createMovieCard(Movie movie) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("/css/style.css");
        vBox.getStyleClass().add("card-main");
        vBox.setSpacing(10);

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(false);
        imageView.setFitHeight(300);
        imageView.setFitWidth(200);
        vBox.getChildren().add(imageView);

        Label title = new Label();
        title.setStyle("/css/style.css");
        title.getStyleClass().add("card-title");
        vBox.getChildren().add(title);

        Label desc = new Label();
        desc.setStyle("/css/style.css");
        desc.getStyleClass().add("card-desc");
        vBox.getChildren().add(desc);

        Label time = new Label();
        time.setStyle("/css/style.css");
        time.getStyleClass().add("card-desc");
        vBox.getChildren().add(time);

        Label price = new Label();
        price.setStyle("/css/style.css");
        price.getStyleClass().add("card-desc");
        vBox.getChildren().add(price);

        Image image1 = new Image(movie.getImagePath());
        imageView.setImage(image1);
        title.setText(movie.getName());
        desc.setText(movie.getDescription());
        time.setText("Start: " + movie.getScreenPlayTime() + "\n length: " + movie.getLength());
        price.setText("Price: " + movie.getPrice() + " USD");

        Button button = new Button("hello");
        button.setStyle("/css/style.css");
        button.setOnAction(event -> System.out.println("hello"));
        vBox.getChildren().add(button);

        return vBox;
    }
}
