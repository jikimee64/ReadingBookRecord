package com.soap.rabbit.chapter7;

/**
 * 손님이라는 개념을 구현한 Customer
 * 메뉴판에서 커피를 정한뒤 주문한 후에
 * 바리스타가 제작한 커피를 받는다.
 *
 * @author 2020.10.10
 * @version 1.0, 작업 내용
 * @see None
 */
public class Customer {
    public void order(String menuName, Menu menu, Barista barista) {
        MenuItem menuItem = menu.choose(menuName);
        Coffee coffee = barista.makeCoffee(menuItem);
        //...
    }
}
