package com.soap.rabbit.chapter7;

import java.util.*;

/**
 * menuName에 해당하는 MenuItem을 찾아야하는 책임이 있는 Menu 클래스
 *
 * @author 2020.10.10
 * @version 1.0, 작업 내용
 * @see None
 */
public class Menu {

    private List<MenuItem> items;

    public Menu(List<MenuItem> items){
        this.items = items;
    }

    public MenuItem choose(String name){
        for(MenuItem each : items){
            if(each.getName().equals(name)){
                return each;
            }
        }
        return null;
    }
}
