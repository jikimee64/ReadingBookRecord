package com.soap.tobyofspringone.chapter3.common;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    Connection makeNewConnection() throws ClassNotFoundException, SQLException;
}