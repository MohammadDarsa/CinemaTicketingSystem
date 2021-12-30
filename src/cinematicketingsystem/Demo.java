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

        Attributes setValues = new Attributes("movie");
        setValues.addAttribute("price_movie", "10");
        Attributes attributes = new Attributes("movie");
        attributes.addAttribute("name_movie", "A Silent Voice");

        dbManager.updateEntity(setValues, attributes, "and", "=");

        List<Movie> movieList = dbManager.selectAll(Movie.class);
        for (Movie m : movieList) {
            System.out.println(m);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
