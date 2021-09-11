package com.soap.tobyofspringone.chapter3.common;

import com.soap.tobyofspringone.chapter3.sub1.InterfaceUserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CountingMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            CountingDaoFactory.class);
        InterfaceUserDao userDao = context.getBean("userDao", InterfaceUserDao.class);

        //
        //DAO 사용 코드
        //...

        // DL을 사용하면 이름을 이용해 어떤 빈이든 가져올 수 있다.
        CountingConnectionMaker ccm = context.getBean("connectionMaker",
            CountingConnectionMaker.class);
        System.out.println(ccm.getCounter());
    }

}