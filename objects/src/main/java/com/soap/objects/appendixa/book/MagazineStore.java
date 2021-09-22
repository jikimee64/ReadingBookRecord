package com.soap.objects.appendixa.book;

public class MagazineStore extends BookStall{

    @Override
    public Book sell(IndependentPublisher independentPublisher){
        return new Magazine(independentPublisher);
    }

}