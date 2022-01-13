package cinematicketingsystem.modules.helloworld;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloWorldController {
    @FXML
    private Button btn;

    @FXML
    void onclick(ActionEvent event) {
        System.out.println("clicked!!!");
    }
}
