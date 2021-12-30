package cinematicketingsystem.modules.btslogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class BtsLoginController {

    @FXML
    private Label text;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btn1;

    @FXML
    void onClick(ActionEvent event) {
        text.setText("borahae");
    }
}
