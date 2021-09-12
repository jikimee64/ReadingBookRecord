package com.soap.tobyofspringone.chapter3.sub6;

import com.soap.tobyofspringone.chapter3.common.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    private RowMapper<User> userRowMapper =
//        new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                User user = new User();
//                user.setId(rs.getString("id"));
//                user.setName(rs.getString("name"));
//                user.setPassword(rs.getString("password"));
//                return user;
//            }
//        };

    //방법2 람다
    private RowMapper<User> userRowMapper2 =
        (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        };


    //두번째 수동 DI 방법
    //Dao 하나당 하나의 디비연결 생성
    @Autowired
    DataSource dataSource;

    public int getCount() {
        //첫번째 콜백, Statement 생성
        //템플릿으로부터 Connection을 받고 PreparedStatement를 돌려준다
        return this.jdbcTemplate.query(
            new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                    return con.prepareStatement("select count(*) from users");
                }
            },
            //두번째 콜백, ResultSet으로부터 값 추출
            //템플릿으로부터 ResultSet을 받고 추출한 결과를 리린다.
            new ResultSetExtractor<Integer>() {
                @Override
                public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                    rs.next();
                    return rs.getInt(1);
                }
            });
    }

    public int getCountV2() {
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    public void add(final User user) throws SQLException, ClassNotFoundException {
        this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
            this.userRowMapper2);
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        this.jdbcTemplate.update(
            con -> con.prepareStatement("delete from users")
        );
    }

    //RowMapper 사용(ResultSet 한 로우의 결과를 오브젝트에 매핑해줌)
    public User get(String id) {
        // Replaced
        return this.jdbcTemplate.queryForObject("SELECT id, name FROM users where id = ?",
            this.userRowMapper2,
            id);
    }


    public List<User> getAll() {
        // Replaced
        return this.jdbcTemplate.query("SELECT * FROM users order by id",
            (this.userRowMapper2));
    }

}