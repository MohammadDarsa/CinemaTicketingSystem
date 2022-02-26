package com.cinematicketsystem.exceptions.registerexception;

public class UserAlreadyFoundException extends Throwable{
    public UserAlreadyFoundException(String message) {
        super(message);
    }
}
