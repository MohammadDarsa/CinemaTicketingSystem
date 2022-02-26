package com.cinematicketsystem.modules.register.registerservice;

import com.cinematicketsystem.exceptions.loginexceptions.PasswordRegexException;
import com.cinematicketsystem.exceptions.loginexceptions.UserNotFoundException;
import com.cinematicketsystem.exceptions.loginexceptions.UsernameRegexException;
import com.cinematicketsystem.exceptions.registerexception.UserAlreadyFoundException;
import com.cinematicketsystem.models.user.customer.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterServiceProxy implements RegisterService {

    RegisterServiceExecuter registerServiceExecuter;

    //at least 1 digit, at least 1 lower case, at least 1 upper case, at least 1 symbol, from 8 to 20 characters
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    //at least 3 characters, a-z capital or small, digits 0 to 9
    private static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,}";

    private static final String VALID_EMAIL_ADDRESS_REGEX ="^(.+)@(.+)$";


    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern emailPattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);


    public RegisterServiceProxy() {
        this.registerServiceExecuter = new RegisterServiceExecuter();
    }



    public Customer register(String username, String password ,  String email, String address, String phone_num, double balance) throws UserNotFoundException, UsernameRegexException, PasswordRegexException, UserAlreadyFoundException {
        if(!validateUsername(username)) throw new UsernameRegexException("Username doesn't follow the pattern try again!");
        if(!validatePassword(password)) throw new PasswordRegexException("Password doesn't follow the pattern try again!");
        if(!validateEmail(email)) throw new PasswordRegexException("email doesn't follow the pattern try again!");

        return registerServiceExecuter.register(username, password,  email,  address, phone_num, balance);
    }

    private boolean validateEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    private boolean validateUsername(String username) {
        Matcher matcher = usernamePattern.matcher(username);
        return matcher.matches();
    }
}
