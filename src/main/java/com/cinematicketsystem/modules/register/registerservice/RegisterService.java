package com.cinematicketsystem.modules.register.registerservice;

import com.cinematicketsystem.exceptions.loginexceptions.PasswordRegexException;
import com.cinematicketsystem.exceptions.loginexceptions.UserNotFoundException;
import com.cinematicketsystem.exceptions.loginexceptions.UsernameRegexException;
import com.cinematicketsystem.exceptions.registerexception.UserAlreadyFoundException;
import com.cinematicketsystem.models.user.customer.Customer;

public interface RegisterService {
    Customer register(String username, String password, String email , String address , String phone_num , double balance ) throws UserNotFoundException, UsernameRegexException, PasswordRegexException, UserAlreadyFoundException;

}
