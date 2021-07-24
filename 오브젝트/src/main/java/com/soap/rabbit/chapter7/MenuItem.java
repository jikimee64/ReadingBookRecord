package com.soap.rabbit.chapter7;

public class MenuItem {
    private String name;
    private int price;

    public MenuItem(String name, int price){
        this.name = name;
        this.price = price;
    }

    public int cost(){
        return price;
    }

    public String getName(){
        return name;
    }
}
