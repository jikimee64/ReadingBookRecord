package com.soap.objects.chapter9;

import com.soap.objects.chapter2.Money;
import com.soap.objects.chapter2.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Client {

    @Autowired
    private Factory factory;

    public Money getAvatarFee(){
        Movie avatar = factory.createAvatarMovie();
        return avatar.getFee();
    }

}