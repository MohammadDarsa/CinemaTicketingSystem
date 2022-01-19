package cinematicketingsystem;


import cinematicketingsystem.models.movie.Movie;
import cinematicketingsystem.models.user.admin.Admin;
import cinematicketingsystem.models.user.customer.Customer;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.util.Date;

public class Demo extends Application {
    private final SceneManager sceneManager = SceneManager.getInstance();
    private final DBManager dbManager = DBManager.getInstance();


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("rin best girl <3");
        sceneManager.setStage(stage);
        sceneManager.switchScene(null, "/view/test.fxml", "style.css");

        Movie movie = new Movie("d", "hello", new Timestamp(100000), 10.2, "dhello", 10.0, new Timestamp(100000), 0, "", null);
        dbManager.insertEntity(movie, Movie.class);
        dbManager.selectAll(Movie.class).forEach(System.out::println);
        //        Date date = new Date();

//        System.out.println(date);
    }

    public static void main(String[] args) {
        launch();
    }
}
