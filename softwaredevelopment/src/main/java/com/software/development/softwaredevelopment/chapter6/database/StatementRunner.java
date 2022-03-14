package com.software.development.softwaredevelopment.chapter6.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementRunner {

    private final Connection conn;

    StatementRunner(final Connection conn) {
        this.conn = conn;
    }

    <R> R extract(final String sql, final Extractor<R> extractor) {
        try(var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.clearParameters();
            return extractor.run(stmt);
        }catch (SQLException e){
            throw new IllegalArgumentException(e);
        }
    }

}
