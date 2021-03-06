package com.jofisaes.mancala.exception;

public class RegistrationMailNotSentException extends RuntimeException {
    public RegistrationMailNotSentException() {
        super("We are sorry, but we could not sent an email to confirm your user registration. " +
                "Please consider this registration cancelled and please try again later");
    }
}
