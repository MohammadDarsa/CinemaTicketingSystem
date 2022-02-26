package com.cinematicketsystem.modules.login.loginservice;

import com.cinematicketsystem.exceptions.loginexceptions.UserNotFoundException;
import com.cinematicketsystem.models.user.customer.Customer;
import com.cinematicketsystem.utils.DBManager;
import com.cinematicketsystem.utils.SceneManager;

import java.io.IOException;
import java.util.Optional;

public class LoginServiceExecutor implements LoginService {
    private DBManager dbManager;
    private SceneManager sceneManager;

    public LoginServiceExecutor() {
        dbManager = DBManager.getInstance();
        sceneManager = SceneManager.getInstance();
    }

    @Override
    public Customer login(String username, String password) throws UserNotFoundException {
        Optional<Customer> customer = dbManager.selectAll(Customer.class).stream().filter(user -> user.getName().equals(username) && user.getPassword().equals(password)).findAny();
        if(customer.isPresent()) {
            try {
                sceneManager.switchScene(null , "/view/movieSelector.fxml");
                return customer.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //TODO: throw an exception
            throw new UserNotFoundException("Please check the entered fields!");
        }
        return null;
    }
}
