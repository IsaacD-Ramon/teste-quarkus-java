package org.acme.service.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ObjectNotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
}
