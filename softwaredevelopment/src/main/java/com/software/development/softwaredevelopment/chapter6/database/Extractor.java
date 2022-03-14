package com.software.development.softwaredevelopment.chapter6.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Extractor<R> {
    R run(PreparedStatement statement) throws SQLException;
}