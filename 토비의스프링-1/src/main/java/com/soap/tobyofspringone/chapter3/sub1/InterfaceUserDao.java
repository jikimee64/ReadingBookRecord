package com.soap.tobyofspringone.chapter3.sub1;

import com.soap.tobyofspringone.chapter1.User;
import com.soap.tobyofspringone.chapter3.common.ConnectionMaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InterfaceUserDao {

    private ConnectionMaker connectionMaker;

    public InterfaceUserDao(
        ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeNewConnection();

        PreparedStatement ps = c.prepareStatement(
            "insert into user(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        //1.관심3
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeNewConnection();

        PreparedStatement ps = c.prepareStatement(
            "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connectionMaker.makeNewConnection();

            ps = c.prepareStatement("select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
            //return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
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

        //try-with-resoureces
        try (Connection c2 = connectionMaker.makeNewConnection();
            PreparedStatement stmt = c.prepareStatement("select count(*) from users");
            ResultSet rs2 = ps.executeQuery();) {
            rs2.next();
            return rs2.getInt(1);
        } catch (SQLException e) {
            throw e;
        }
    }


    public void deleteAll() throws SQLException, ClassNotFoundException {

        //책 코드
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.makeNewConnection();
            ps = c.prepareStatement("delete from users");
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

        //try-with-resoureces
        try (Connection c2 = connectionMaker.makeNewConnection();
            PreparedStatement ps2 = c2.prepareStatement(
                "delete from users");
        ) {
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }


}
