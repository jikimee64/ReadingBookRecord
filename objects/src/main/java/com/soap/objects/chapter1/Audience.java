package com.soap.objects.chapter1;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }

    public long buy(Ticket ticket){
        return bag.hold(ticket);
    }

}
