package com.soap.objects.appendixa;

public class EmptyCallException extends RuntimeException{

    public EmptyCallException() {
    }

    public EmptyCallException(String message) {
        super(message);
    }

    public EmptyCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyCallException(Throwable cause) {
        super(cause);
    }
}
