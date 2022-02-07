package com.software.development.softwaredevelopment.chapter3.bookcode3.exception_example;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private final List<String> errors = new ArrayList<>();

    public void addError(final String message){
        errors.add(message);
    }

    public boolean hasErrors(){
        return !errors.isEmpty();
    }

    public String errorMessage(){
        return errors.toString();
    }

    public List<String> getErrors(){
        return this.errors;
    }

    public Notification validate(){
        final Notification notification = new Notification();
        if(this.de)
    }

}