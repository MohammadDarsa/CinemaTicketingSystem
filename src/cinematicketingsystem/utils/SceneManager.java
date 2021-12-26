package cinematicketingsystem.utils;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class SceneManager {
    private static SceneManager sceneManager = new SceneManager();

    private Stage stage;
    private Scene scene;
    private Parent root;

    private SceneManager() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public static SceneManager getInstance() {
        return sceneManager;
    }

    public void switchScene(Event event, String path) throws IOException {
        root = FXMLLoader.load(getClass().getResource(path));
        if(event != null)
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
