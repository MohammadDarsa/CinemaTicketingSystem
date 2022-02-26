package com.cinematicketsystem.utils;

import com.cinematicketsystem.models.movie.Movie;
import com.cinematicketsystem.modules.confirmation.ConfirmationController;
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

    public void switchScene(Event event, String path, String styleSheet) throws IOException {
        root = FXMLLoader.load(getClass().getResource(path));
        root.getStylesheets().add(getClass().getResource("/css/"+styleSheet).toString());
        if(event != null)
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void createConfirmationPopup(String path, Movie movie) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        Parent window = fxmlLoader.load();
        ConfirmationController confirmationController = fxmlLoader.getController();
        confirmationController.setData(movie);
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(window);
        stage1.setScene(scene1);
        stage1.show();
    }
}
