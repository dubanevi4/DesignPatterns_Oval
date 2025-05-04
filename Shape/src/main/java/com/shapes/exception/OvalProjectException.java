package com.shapes.exception;

public class OvalProjectException extends Exception{
    // Without parameters
    public OvalProjectException() {
        super("Error in Oval project");
    }

    // With message
    public OvalProjectException(String message) {
        super(message);
    }

    // With throwable exception
    public OvalProjectException(Throwable cause) {
        super(cause);
    }

    // With message and cause
    public OvalProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
