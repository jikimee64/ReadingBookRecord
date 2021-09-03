package com.soap.objects.chapter1;
/**
 * 관람객이라는 개념을 구현한 Audience
 * 소지품을 보관하긴 위한 가방 인스턴스 변수
 *
 * @author 2020.10.05
 * @version 1.0, 작업 내용
 */
public class Audience {
    private Bag bag;

    public Audience(Bag bag){
        this.bag = bag;
    }

    public Bag getBag(){
        return bag;
    }
}
