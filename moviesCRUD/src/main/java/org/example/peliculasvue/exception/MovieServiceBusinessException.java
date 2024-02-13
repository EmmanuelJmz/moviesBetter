package org.example.peliculasvue.exception;

public class MovieServiceBusinessException extends RuntimeException{
    public MovieServiceBusinessException(String message){
        super(message);
    }
}
