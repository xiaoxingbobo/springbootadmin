package com.xxbb.springbootapi.interceptor;

public class LegalException extends RuntimeException{
    public LegalException(String message) {
        super(message);
    }
}
