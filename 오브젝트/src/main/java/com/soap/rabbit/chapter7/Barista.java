package com.soap.rabbit.chapter7;

/**
 * 건네받은 MenuItem을 이용해 커피를 제조
 *
 * @author 2020.10.10
 * @version 1.0, 작업 내용
 * @see None
 */
public class Barista {
    public Coffee makeCoffee(MenuItem menuItem) {
        Coffee coffee = new Coffee(menuItem);
        return coffee;
    }
}
