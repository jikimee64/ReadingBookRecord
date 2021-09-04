package com.soap.tobyofspringone.chapter1.addcounting;

import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;
import com.soap.tobyofspringone.chapter1.interfacecode.DConnectionMaker;
import com.soap.tobyofspringone.chapter1.interfacecode.InterfaceUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public InterfaceUserDao userDao() {
        return new InterfaceUserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }

}
