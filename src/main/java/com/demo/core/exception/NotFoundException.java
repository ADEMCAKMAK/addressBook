package com.demo.core.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Not found entity");
    }
}
