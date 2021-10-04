package com.soap.objects.appendixac;

public class Troll extends Monster{

    public Troll() {
        super(48);
    }

    @Override
    public String getAttack() {
        return "트롤은 곤봉으로 때린다.";
    }

}