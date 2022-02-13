package cinematicketingsystem.modules.sidenav;

import cinematicketingsystem.utils.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class SideNavController implements Initializable {

    @FXML
    private VBox sideNav;

    @FXML
    private HBox homeBtn;

    @FXML
    private HBox moviesBtn;

    @FXML
    private HBox ticketsBtn;

    @FXML
    private HBox logoutBtn;

    private SceneManager sceneManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = SceneManager.getInstance();
    }

    @FXML
    @SneakyThrows
    void homeClick(MouseEvent event) {
        sceneManager.switchScene(event, "/view/user.fxml", "style.css");
    }

    @FXML
    @SneakyThrows
    public void logoutClicked(MouseEvent event) {
        sceneManager.switchScene(event, "/view/login.fxml", "style.css");
    }

    @FXML
    @SneakyThrows
    public void moviesClicked(MouseEvent event) {
        sceneManager.switchScene(event, "/view/movieSelector.fxml", "style.css");
    }

    @FXML
    @SneakyThrows
    public void ticketsClicked(MouseEvent event) {
        sceneManager.switchScene(event, "/view/ticketSelector.fxml");
    }

}
