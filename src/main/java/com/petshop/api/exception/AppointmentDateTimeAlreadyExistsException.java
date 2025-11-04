package com.petshop.api.exception;

public class AppointmentDateTimeAlreadyExistsException extends RuntimeException {
    public AppointmentDateTimeAlreadyExistsException(String message) {
        super(message);
    }
}
