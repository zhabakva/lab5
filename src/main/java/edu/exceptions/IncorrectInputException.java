package edu.exceptions;
/**
 * Is thrown because of incorrect input
 * **/
public class IncorrectInputException extends Exception{
    public IncorrectInputException(String message){
        super(message);
    }
}
