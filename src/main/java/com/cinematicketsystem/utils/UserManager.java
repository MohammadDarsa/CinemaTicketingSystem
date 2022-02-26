package com.cinematicketsystem.utils;


import com.cinematicketsystem.models.user.customer.Customer;
import lombok.Getter;
import lombok.Setter;

public class UserManager {
    private static UserManager userManager;

    @Getter
    @Setter
    private Customer customer;

    private  UserManager() {}

    public static UserManager getInstance() {
        if(userManager == null) userManager = new UserManager();
        return userManager;
    }
}
