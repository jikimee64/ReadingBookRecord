package com.soap.objects.appendixac;

public class Dragon extends Monster{

    public Dragon() {
        super(230);
    }

    @Override
    public String getAttack(){
        return "용은 불을 뿜는다.";
    }

}