package com.example.homework_89.exception;

public class AppBadRequestException extends RuntimeException{
    public AppBadRequestException(String message) {
        super(message);
    }
}
