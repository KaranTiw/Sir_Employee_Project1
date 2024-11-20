package com.in.sp.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {

        super(msg);
    }
}
