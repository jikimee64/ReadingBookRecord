package com.soap.tobyofspringone.chapter1.abstractcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DuserDao extends abstractUserDao {

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // D사 DB connection 코드
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost/springbook", "spring", "book");
    }

}
