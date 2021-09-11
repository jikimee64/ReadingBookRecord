package com.soap.tobyofspringone.chapter3.sub5;

import com.soap.tobyofspringone.chapter3.common.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterfaceUserDao {

    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    //두번째 수동 DI 방법
    //Dao 하나당 하나의 디비연결 생성
    @Autowired
    DataSource dataSource;

    public void setDataSource() {
        this.jdbcContext = new JdbcContext();
        this.jdbcContext.setDataSource(dataSource);
        this.dataSource = dataSource;
    }

    public void add(final User user) throws SQLException, ClassNotFoundException {
        this.jdbcContext.executeAddSql("insert into users(id, name, password) values(?, ?, ?)",
            user);
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        this.jdbcContext.executeSql("delete from users");
    }

}