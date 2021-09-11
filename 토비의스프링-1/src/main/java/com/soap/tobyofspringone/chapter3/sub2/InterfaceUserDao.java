package com.soap.tobyofspringone.chapter3.sub2;

import com.soap.tobyofspringone.chapter3.common.ConnectionMaker;
import com.soap.tobyofspringone.chapter3.common.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InterfaceUserDao {

    private ConnectionMaker connectionMaker;

    public InterfaceUserDao(
        ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy st = new AddStatement(user);
        jdbcContextWithStatementStrategy(st);
    }

    //client
    public void deleteAll() throws SQLException, ClassNotFoundException {
        StatementStrategy st = new DeleteAllStatement(); //선정한 전략 클래스의 오브젝트 생성
        jdbcContextWithStatementStrategy(st); //컨텍스트 호출, 전략 오브젝트 전달
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException {
        //책 코드
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.makeNewConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            //close는 만들어진 순서의 반대로 하는것이 원칙
            if (ps != null) {
                try {
                    ps.close(); //SQL Exception이 발생할 수 있음
                } catch (SQLException e) {

                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }


}
