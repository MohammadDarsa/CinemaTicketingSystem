package cinematicketingsystem;

import cinematicketingsystem.models.Movie;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class Demo extends Application {
    private SceneManager sceneManager = SceneManager.getInstance();
    private DBManager dbManager = DBManager.getInstance();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("rin best girl <3");
        sceneManager.setStage(stage);
        sceneManager.switchScene(null, "/view/helloWorld.fxml");

        List<Movie> list = dbManager.executeQueryFromFile("/sql/test.sql", Movie.class);
        for (Movie m :list) {
            System.out.println(m);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
