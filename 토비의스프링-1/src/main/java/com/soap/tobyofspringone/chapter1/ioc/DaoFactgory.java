package com.soap.tobyofspringone.chapter1.ioc;

import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;
import com.soap.tobyofspringone.chapter1.interfacecode.DConnectionMaker;
import com.soap.tobyofspringone.chapter1.interfacecode.InterfaceUserDao;

public class DaoFactgory {
    public InterfaceUserDao userDao(){
        ConnectionMaker connectionMaker = new DConnectionMaker();
        InterfaceUserDao userDao = new InterfaceUserDao(connectionMaker);
        return userDao;
    }

    public AccountDao accountDao(){
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao(){
        return new MessageDao(connectionMaker());
    }

    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}