package com.kwc.ch2reactive.ch2reactive;

class ClassA{
    ClassA(){
        System.out.println('A');
        this.prn();
    }

    void prn(){
        System.out.println('B');
    }
}

class ClassB extends ClassA {

    public ClassB() {
        super();
        System.out.println('D');
    }

    void prn(int x){
        System.out.println(x);
    }
}

public class main {

    public static void main(String[] args) {
        int x = 7;
        ClassB cal = new ClassB();
        cal.prn(7);
    }


}
