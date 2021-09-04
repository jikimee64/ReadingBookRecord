package com.soap.tobyofspringone.chapter1.ioc;

import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;

public class MessageDao {
    private ConnectionMaker connectionMaker;

    public MessageDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

}
