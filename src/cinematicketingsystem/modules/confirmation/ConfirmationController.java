package cinematicketingsystem.modules.confirmation;

import cinematicketingsystem.models.movie.Movie;
import cinematicketingsystem.models.seat.Seat;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationController {

    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField movieName;

    @FXML
    private TextField numberOfTickets;

    @FXML
    private TextField seatId;

    @FXML
    private TextField date;

    @FXML
    private TextField roomId;

    @FXML
    private TextField movieStartTime;

    @FXML
    private TextField price;

    @FXML
    private TextField balance;

    private UserManager userManager;
    private Movie movie;
    private DBManager dbManager;
    private int seatid;


    @FXML
    void cancel(ActionEvent event) {
        balance.getScene().getWindow().hide();
    }

    @FXML
    void confirm(ActionEvent event) {
        if(movie.getPrice() <= userManager.getCustomer().getBalance()){
            dbManager.buyTicket(movie.getId(), userManager.getCustomer().getId(), seatid);
            balance.getScene().getWindow().hide();
        }
    }

    public void setData(Movie movie) {
        dbManager = DBManager.getInstance();
        this.movie = movie;
        userManager = UserManager.getInstance();
        movieName.setText(movie.getName());
        movieStartTime.setText(movie.getLength().toString());
        date.setText(movie.getScreenPlayTime().toString());
        price.setText(movie.getPrice().toString());
        balance.setText(userManager.getCustomer().getBalance().toString());
        seatid = dbManager.getEmptySeatFromMovie(movie.getId());
        seatId.setText(String.valueOf(seatid));
        roomId.setText(String.valueOf(dbManager.getRoomFromMovie(movie.getId())));
        numberOfTickets.setText("1");
    }
}
