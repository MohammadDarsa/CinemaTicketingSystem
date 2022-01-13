package cinematicketingsystem;


import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Demo extends Application {
    private final SceneManager sceneManager = SceneManager.getInstance();
    private final DBManager dbManager = DBManager.getInstance();
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("rin best girl <3");
        sceneManager.setStage(stage);
        sceneManager.switchScene(null, "/view/helloWorld.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
