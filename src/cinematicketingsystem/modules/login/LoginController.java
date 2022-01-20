package cinematicketingsystem.modules.login;

import cinematicketingsystem.exceptions.loginexceptions.PasswordRegexException;
import cinematicketingsystem.exceptions.loginexceptions.UserNotFoundException;
import cinematicketingsystem.exceptions.loginexceptions.UsernameRegexException;
import cinematicketingsystem.modules.login.loginservice.LoginService;
import cinematicketingsystem.modules.login.loginservice.LoginServiceProxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private LoginService loginService;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginService = new LoginServiceProxy();
    }

    @FXML
    void login(ActionEvent event) {
        try {
            loginService.login(usernameText.getText(), passwordText.getText());
        } catch (UserNotFoundException | PasswordRegexException | UsernameRegexException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
