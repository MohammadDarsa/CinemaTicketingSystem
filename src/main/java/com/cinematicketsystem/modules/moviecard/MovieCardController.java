package com.cinematicketsystem.modules.moviecard;

import com.cinematicketsystem.models.movie.Movie;
import com.cinematicketsystem.utils.DBManager;
import com.cinematicketsystem.utils.SceneManager;
import com.cinematicketsystem.utils.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
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
    private Movie movie;
    private UserManager userManager;
    private SceneManager sceneManager;

    @FXML
    public void bookTicket(ActionEvent event) {
        //TODO: implement this shit
        try {
            sceneManager.createConfirmationPopup("/view/confirmation.fxml", movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(Movie movie) {
        this.movie = movie;
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
        sceneManager = SceneManager.getInstance();
        userManager = UserManager.getInstance();
    }
}
