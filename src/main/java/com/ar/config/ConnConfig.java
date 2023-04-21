package com.ar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnConfig {

    public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SQL_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=proyecto_ar;encrypt=false;";
    public static final String SQL_USER = "sa";
    public static final String SQL_PASS = "mssql1Ipw";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        Connection conn;
        conn = DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASS);
        return conn;
    }

    public static void close(Connection conn) throws SQLException {
        if(conn == null) return;
        conn.close();
    }

    public static void close(PreparedStatement statement) throws SQLException {
        if(statement == null) return;
        statement.close();
    }

    public static void close(ResultSet result) throws SQLException {
        if(result == null) return;
        result.close();
    }
}
