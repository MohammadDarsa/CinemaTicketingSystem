package com.cinematicketsystem.exceptions.sqlexceptions;

public class EntityNotFoundException extends Throwable {
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
