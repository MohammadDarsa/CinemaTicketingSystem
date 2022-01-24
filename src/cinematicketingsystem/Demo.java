package cinematicketingsystem;


import cinematicketingsystem.models.category.Category;
import cinematicketingsystem.models.movie.Movie;
import cinematicketingsystem.models.room.Room;
import cinematicketingsystem.models.seat.Seat;
import cinematicketingsystem.models.ticket.Ticket;
import cinematicketingsystem.models.user.admin.Admin;
import cinematicketingsystem.models.user.customer.Customer;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;


public class Demo extends Application {
    private final SceneManager sceneManager = SceneManager.getInstance();
    private final DBManager dbManager = DBManager.getInstance();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("GUI project");
        sceneManager.setStage(stage);
        sceneManager.switchScene(null, "/view/login.fxml", "style.css");

        //TODO: ORM Relations
        dbManager.selectAll(Movie.class).forEach(System.out::println);
        dbManager.selectAll(Category.class).forEach(System.out::println);
        dbManager.selectAll(Seat.class).forEach(System.out::println);
        dbManager.selectAll(Ticket.class).forEach(System.out::println);
        dbManager.selectAll(Admin.class).forEach(System.out::println);
        dbManager.selectAll(Room.class).forEach(System.out::println);
        dbManager.selectAll(Customer.class).forEach(System.out::println);
    }

    public static void main(String[] args) {
        launch();
    }
}
