package com.mugil.org.exception;

public class LocationNotAllowedException extends RuntimeException{
    public String errorMsg;

    public LocationNotAllowedException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LocationNotAllowedException(String message, String errorMsg) {
        super(message);
        this.errorMsg = errorMsg;
    }

    public LocationNotAllowedException(String message, Throwable cause, String errorMsg) {
        super(message, cause);
        this.errorMsg = errorMsg;
    }
}
