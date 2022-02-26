package com.cinematicketsystem.modules.register;

import com.cinematicketsystem.modules.register.registerservice.RegisterService;
import com.cinematicketsystem.modules.register.registerservice.RegisterServiceProxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {


    @FXML
    private TextField address;

    @FXML
    private TextField balance;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private Button register;

    @FXML
    private TextField username;

    RegisterService registerService;


    @SneakyThrows
    @FXML
    void register(ActionEvent event) {
        registerService.register(username.getText(), password.getText(),email.getText(),address.getText(),phone.getText(), Double.parseDouble(balance.getText()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerService = new RegisterServiceProxy();

    }
}
