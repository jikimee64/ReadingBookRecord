package com.soap.tobyofspringone.chapter1.spring;

import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;
import com.soap.tobyofspringone.chapter1.interfacecode.DConnectionMaker;
import com.soap.tobyofspringone.chapter1.interfacecode.InterfaceUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보
public class SpringDaoFactory {
    @Bean //오브젝트 생성을 담당하는 IoC용 멧모드
    public InterfaceUserDao userDao(){
        return new InterfaceUserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }


}
