package com.soap.objects.chapter11;

import java.util.Hashtable;

//Hashtable과 Properties의 상속관계를 합성관계로 품
//부모 클래스의 클래스를 자식클래스의 인스턴스 필드로 추가
//불필요한 오퍼레이션들이 퍼블릭 인터페이스에 스며드는 것을 방지하기 위한 목적
public class Properties {
    private Hashtable<String, String> properties = new Hashtable<>();

    public String setProperty(String key, String value){
        return properties.put(key, value);
    }

    public String getProperty(String key){
        return properties.get(key);
    }

}