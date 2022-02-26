package com.cinematicketsystem.modules.login;

import com.cinematicketsystem.exceptions.loginexceptions.PasswordRegexException;
import com.cinematicketsystem.exceptions.loginexceptions.UserNotFoundException;
import com.cinematicketsystem.exceptions.loginexceptions.UsernameRegexException;
import com.cinematicketsystem.modules.login.loginservice.LoginService;
import com.cinematicketsystem.modules.login.loginservice.LoginServiceProxy;
import com.cinematicketsystem.utils.SceneManager;
import com.cinematicketsystem.utils.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

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
    private Button register;

    @FXML
    private Button loginBtn;

    private UserManager userManager;
    private SceneManager sceneManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginService = new LoginServiceProxy();
        userManager = UserManager.getInstance();
        sceneManager = SceneManager.getInstance();
    }

    @FXML
    void login(ActionEvent event) {
        try {
            userManager.setCustomer(loginService.login(usernameText.getText(), passwordText.getText()));
        } catch (UserNotFoundException | PasswordRegexException | UsernameRegexException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @SneakyThrows
    @FXML
    void registeration(ActionEvent event) {
        sceneManager.switchScene(event,"/view/registerForm.fxml");
    }

}
