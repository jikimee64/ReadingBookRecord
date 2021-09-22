package com.soap.objects.appendixa.book;

public class Main {

    public static void main(String[] args) {
        new Customer().order(new BookStall());
        new Customer().order(new MagazineStore());
    }

}
