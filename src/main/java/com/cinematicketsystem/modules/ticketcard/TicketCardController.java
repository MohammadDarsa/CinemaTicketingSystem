package com.cinematicketsystem.modules.ticketcard;

import com.cinematicketsystem.models.movie.Movie;
import com.cinematicketsystem.models.ticket.Ticket;
import com.cinematicketsystem.utils.DBManager;
import com.cinematicketsystem.utils.SceneManager;
import com.cinematicketsystem.utils.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketCardController implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private Label title;

    @FXML
    private Label time;

    @FXML
    private Label price;

    @FXML
    private Label timeBooked;

    @FXML
    private Label seat;

    @FXML
    private Label room;

    private DBManager dbManager;
    private Movie movie;
    private Ticket ticket;
    private UserManager userManager;
    private SceneManager sceneManager;

    public void setData(Ticket ticket) {
        this.movie = ticket.getMovie();
        this.ticket = ticket;
        Image image1 = new Image(movie.getImagePath());
        image.setImage(image1);
        title.setText(movie.getName());
        time.setText("Start: " + movie.getScreenPlayTime() + "\n length: " + movie.getLength());
        price.setText("Price: " + movie.getPrice() + " USD");
        timeBooked.setText("Time Booked: " + ticket.getTimeBooked().toString());
        seat.setText("Seat: " + ticket.getSeat().getId().toString());
        room.setText("Room: " + ticket.getSeat().getRoomId().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbManager = DBManager.getInstance();
        sceneManager = SceneManager.getInstance();
        userManager = UserManager.getInstance();
    }

}
