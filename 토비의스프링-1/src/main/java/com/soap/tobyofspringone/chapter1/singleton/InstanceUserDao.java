package com.soap.tobyofspringone.chapter1.singleton;

import com.soap.tobyofspringone.chapter1.User;
import com.soap.tobyofspringone.chapter1.interfacecode.ConnectionMaker;
import java.sql.Connection;
import java.sql.SQLException;

public class InstanceUserDao {
    //초기 설정시 바뀌지 않는 읽기전용 인스턴스 변수
    private ConnectionMaker connectionMaker;

    //매번 새로운 값으로 바뀌는 정보를 담은 인스턴스 변수
    private Connection c;
    private User user;
    //

    public User get(String id) throws ClassNotFoundException, SQLException {
        this.c = connectionMaker.makeNewConnection();

        this.user = new User();
        this.user.setId("DB에서 가져온 ID");
        this.user.setName("DB에서 가져온 이름");
        this.user.setPassword("DB에서 가져온 패스워드");
        //...
        return this.user;
    }

}
