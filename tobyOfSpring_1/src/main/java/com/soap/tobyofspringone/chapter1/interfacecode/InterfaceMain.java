package com.soap.tobyofspringone.chapter1.interfacecode;

public class InterfaceMain {
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        InterfaceUserDao userDao = new InterfaceUserDao(connectionMaker);
    }
}