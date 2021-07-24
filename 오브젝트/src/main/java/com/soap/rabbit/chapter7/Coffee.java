package com.soap.rabbit.chapter7;

/**
 * 자기 자신을 생성하기 위한 생성자를 제공
 * 생성자 안에서 MenuItem에 요청을 보낸 뒤 이름과 가격을 얻은 후
 * Coffee 소성에 저장
 *
 * @author 2020.10.10
 * @version 1.0, 작업 내용
 * @see None
 */
public class Coffee {
    private String name;
    private int price;

    public Coffee(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.price = menuItem.cost();
    }
}
