package com.flight.exceptions;


import org.springframework.security.authentication.BadCredentialsException;

public class InvalidCredentials extends BadCredentialsException {
    private String msg;

    public InvalidCredentials(String msg) {
        super(msg);
        this.msg=msg;
    }
}