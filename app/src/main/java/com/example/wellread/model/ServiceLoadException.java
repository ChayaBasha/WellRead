package com.example.wellread.model;

public class ServiceLoadException extends Exception {

    private static final long serialVersionUID = 1L;

    public ServiceLoadException(final String inMessage) {
        super(inMessage);
    }
}
