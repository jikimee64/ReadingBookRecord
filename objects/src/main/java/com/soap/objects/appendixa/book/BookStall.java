package com.soap.objects.appendixa.book;

public class BookStall {

    public Book sell(IndependentPublisher independentPublisher){
        return new Book(independentPublisher);
    }

}