package com.soap.tobyofspringone.chapter1.spring;

import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;
import com.soap.tobyofspringone.chapter1.ioc.DaoFactgory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//단점 : 코드안에 오브젝트 팩토리 클래스나 스프링 API가 나타남
//다만 필이 사용해야할 때 : main()메소드 등 에서 애플리케이션 기동 시점에서는 적어도 한번은 검색방식으로
//가져와야함
//서버도 마찬가지이만 다행히, 서블릿에서 이미 구현해놓음
//검색방식만의 특징 : LookupUserDao는 빈일 필요가 없다. ConnectionMaker만 빈이면 된다
//주입 방식은 LookupUserDao도 반드시 빈이여야 한다.
public class LookupUserDao {

    ConnectionMaker connectionMaker;

    //방법1
    //의존관계 lookup(검색)
    //외부로부터 주입이 아니라 스스로 IoC 컨테이너인 DaoFactory에게 요청
//    public LookupUserDao() {
//        DaoFactgory daoFactgory = new DaoFactgory();
//        this.connectionMaker = daoFactgory.connectionMaker();
//    }

    //방법2
    public LookupUserDao() {
        AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(DaoFactgory.class);
        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    }

}