package cinematicketingsystem;


import cinematicketingsystem.exceptions.sqlExceptions.EntityNotFoundException;
import cinematicketingsystem.models.Movie;
import cinematicketingsystem.utils.Attributes;
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
//        stage.setTitle("rin best girl <3");
//        sceneManager.setStage(stage);
//        sceneManager.switchScene(null, "/view/helloWorld.fxml");
        Attributes attributes = new Attributes("movie");
        attributes.addAttribute("name_movie", "helloWorld");
        attributes.addAttribute("desc_movie", "umu");
        attributes.addAttribute("tor_movie", "2022-01-01");
        attributes.addAttribute("price_movie", "3");
        dbManager.insertEntity(attributes);
        List<Movie> movieList = dbManager.selectAll(Movie.class);
        for (Movie m : movieList) {
            System.out.println(m);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
