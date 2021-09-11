package com.soap.tobyofspringone.chapter1.spring;

import com.soap.tobyofspringone.chapter1.interfacecode.InterfaceUserDao;
import com.soap.tobyofspringone.chapter1.ioc.DaoFactgory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMain {

    // 애플리케이션 컨텍스트 적용
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactgory.class);

        //getBean은 기본적으로 Object 타입으로 리턴하므로 리턴타이 설정시 불필요한 캐스팅 X
        //getBean("빈의 이름", "리턴타입") : 애플리케이션 컨텍스트가 관리하는 오브젝트를 요청하는 메소드
        InterfaceUserDao userDao = context.getBean("userDao", InterfaceUserDao.class);
    }

}
