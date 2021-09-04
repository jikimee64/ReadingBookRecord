package com.soap.tobyofspringone.chapter1.addcounting;

import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;
import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {

    int counter = 0;
    private ConnectionMaker connectionMaker;

    public CountingConnectionMaker(
        ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    @Override
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return connectionMaker.makeNewConnection();
    }

    public int getCounter() {
        return counter;
    }
}
