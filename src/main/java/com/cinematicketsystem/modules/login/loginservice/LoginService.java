package com.cinematicketsystem.modules.login.loginservice;

import com.cinematicketsystem.exceptions.loginexceptions.PasswordRegexException;
import com.cinematicketsystem.exceptions.loginexceptions.UserNotFoundException;
import com.cinematicketsystem.exceptions.loginexceptions.UsernameRegexException;
import com.cinematicketsystem.models.user.customer.Customer;

public interface LoginService {
    Customer login(String username, String password) throws UserNotFoundException, UsernameRegexException, PasswordRegexException;
}
