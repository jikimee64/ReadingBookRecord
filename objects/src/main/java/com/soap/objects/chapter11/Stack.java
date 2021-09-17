package com.soap.objects.chapter11;

import java.util.EmptyStackException;
import java.util.Vector;

//Vector와 Stack의 상속관계를 합성관계로 품
//부모 클래스의 클래스를 자식클래스의 인스턴스 필드로 추가
//불필요한 오퍼레이션들이 퍼블릭 인터페이스에 스며드는 것을 방지하기 위한 목적
public class Stack<E> {
    private Vector<E> elements = new Vector<>();

    public E push(E item){
        elements.addElement(item);
        return item;
    }

    public E pop(){
        if(elements.isEmpty()){
            throw new EmptyStackException();
        }
        return elements.remove(elements.size() - 1);
    }

}