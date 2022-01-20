package cinematicketingsystem;


import cinematicketingsystem.models.movie.Movie;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;


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
    }

    public static void main(String[] args) {
        launch();
    }
}
