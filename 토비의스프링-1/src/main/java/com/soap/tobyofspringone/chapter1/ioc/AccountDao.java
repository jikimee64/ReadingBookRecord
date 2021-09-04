package com.soap.tobyofspringone.chapter1.ioc;

import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;

public class AccountDao {

    private ConnectionMaker connectionMaker;

    public AccountDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
