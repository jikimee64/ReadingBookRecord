package com.soap.tobyofspringone.chapter3.sub3;

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

    public void add(final User user) throws SQLException, ClassNotFoundException {

        //익명 내부 클래스
//        StatementStrategy st = new StatementStrategy(){
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//
//                return ps;
//            }
//        };

        //람다
        StatementStrategy st = c -> {
            PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            return ps;
        };

        jdbcContextWithStatementStrategy(
            c -> {
                PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        );

    }

    //client
    public void deleteAll() throws SQLException, ClassNotFoundException {
        jdbcContextWithStatementStrategy(
            c -> {
                PreparedStatement ps = c.prepareStatement("delete from users");
                return ps;
            }
        );
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
