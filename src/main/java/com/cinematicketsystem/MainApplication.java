package com.cinematicketsystem;

import com.cinematicketsystem.utils.DBManager;
import com.cinematicketsystem.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private final SceneManager sceneManager = SceneManager.getInstance();
    private final DBManager dbManager = DBManager.getInstance();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cinema System");
        sceneManager.setStage(primaryStage);
        sceneManager.switchScene(null, "/view/login.fxml", "style.css");
    }
}
