package cinematicketingsystem.modules.moviecard;

import cinematicketingsystem.models.movie.Movie;
import cinematicketingsystem.utils.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieCardController implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Label title;

    @FXML
    private Label desc;

    @FXML
    private Button btn;

    @FXML
    private Label time;

    @FXML
    private Label price;

    private DBManager dbManager;

    @FXML
    public void bookTicket(ActionEvent event) {
        System.out.println("Hellooo");
    }

    public void setData(Movie movie) {
        Image image1 = new Image(movie.getImagePath());
        image.setImage(image1);
        title.setText(movie.getName());
        desc.setText(movie.getDescription());
        time.setText("Start: " + movie.getScreenPlayTime() + "\n length: " + movie.getLength());
        price.setText("Price: " + movie.getPrice() + " USD");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbManager = DBManager.getInstance();
    }
}
