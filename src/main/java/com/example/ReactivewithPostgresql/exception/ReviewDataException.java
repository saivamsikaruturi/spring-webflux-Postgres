package com.example.ReactivewithPostgresql.exception;

import java.util.List;

public class ReviewDataException extends RuntimeException {

    private String message;
    public ReviewDataException(String error) {
        super(error);
        this.message=error;
    }
}
