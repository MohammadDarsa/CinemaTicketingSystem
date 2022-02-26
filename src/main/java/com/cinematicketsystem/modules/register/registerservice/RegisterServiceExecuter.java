package com.cinematicketsystem.modules.register.registerservice;

import com.cinematicketsystem.exceptions.loginexceptions.PasswordRegexException;
import com.cinematicketsystem.exceptions.loginexceptions.UserNotFoundException;
import com.cinematicketsystem.exceptions.loginexceptions.UsernameRegexException;
import com.cinematicketsystem.exceptions.registerexception.UserAlreadyFoundException;
import com.cinematicketsystem.models.user.customer.Customer;
import com.cinematicketsystem.utils.DBManager;
import com.cinematicketsystem.utils.SceneManager;

import java.io.IOException;
import java.util.Optional;

public class RegisterServiceExecuter implements RegisterService {

    private DBManager dbManager;
    private SceneManager sceneManager;

    public RegisterServiceExecuter() {
        dbManager = DBManager.getInstance();
        sceneManager = SceneManager.getInstance();
    }


    @Override
    public Customer register(String username, String password, String email, String address, String phone_num, double balance) throws UserNotFoundException, UsernameRegexException, PasswordRegexException, UserAlreadyFoundException {
        Optional<Customer> customer = dbManager.selectAll(Customer.class).stream().filter(user -> user.getName().equals(username)).findAny();
        if(!customer.isPresent()) {
            try {
                Customer customer1= new Customer().setAddress(address).setBalance(balance).
                        setEmail(email).setName(username).
                        setPassword(password).setPhone(Integer.valueOf(phone_num));
                dbManager.insertEntity(customer1,Customer.class);
                sceneManager.switchScene(null , "/view/login.fxml");
                return customer.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //TODO: throw an exception
            throw new UserAlreadyFoundException("User already exists !");
        }


        return null;
    }
}
