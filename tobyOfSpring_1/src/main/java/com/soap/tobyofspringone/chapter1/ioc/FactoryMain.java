package com.soap.tobyofspringone.chapter1.ioc;

import com.soap.tobyofspringone.chapter1.interfacecode.InterfaceUserDao;

/**
 * Ioc 개념 적용
 */
public class FactoryMain {

    public static void main(String[] args) {
        InterfaceUserDao userDao = new DaoFactgory().userDao();
    }

}
