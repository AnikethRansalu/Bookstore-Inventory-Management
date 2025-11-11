package com.bookstore.dao;

import com.bookstore.utils.DBConnection;
import java.sql.Connection;

public class BaseDAO {

    protected Connection getConnection() {
        return DBConnection.getConnection();
    }
}
