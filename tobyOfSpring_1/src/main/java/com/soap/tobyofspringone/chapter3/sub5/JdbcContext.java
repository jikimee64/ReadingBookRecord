package com.soap.tobyofspringone.chapter3.sub5;

import com.soap.tobyofspringone.chapter3.common.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcContext {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        //책 코드
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = this.dataSource.getConnection();
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

    public void executeAddSql(final String query, final User... users)
        throws SQLException, ClassNotFoundException {

        for (User user : users) {
            workWithStatementStrategy(
                c -> {
                    PreparedStatement ps = null;
                    ps = c.prepareStatement(
                        query);
                    ps.setString(1, user.getId());
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getPassword());
                    return ps;
                });
        }
    }

    public void executeSql(final String query) throws SQLException, ClassNotFoundException {
        workWithStatementStrategy(
            c -> {
                PreparedStatement ps = c.prepareStatement(query);
                return ps;
            }
        );
    }

}
