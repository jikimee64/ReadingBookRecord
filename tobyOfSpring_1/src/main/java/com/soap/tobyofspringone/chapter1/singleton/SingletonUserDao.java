package com.soap.tobyofspringone.chapter1.singleton;

import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;

public class SingletonUserDao {
    private static SingletonUserDao INSTANCE;

    private SingletonUserDao() {
        //
    }

    public static synchronized SingletonUserDao getInstance(){
        if(INSTANCE == null) INSTANCE = new SingletonUserDao();
        return INSTANCE;
    }
}
