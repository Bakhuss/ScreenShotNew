package ru.bakhuss.ScreenShotNew.dataBase;

import java.sql.*;

public class SQLHandler {

    private Connection connection;
    private Statement stms;
    private PreparedStatement pstmt;
    private String dbType;
    private String user = null;
    private String password = null;

    SQLHandler(String dbType) {
        this.dbType = dbType;
    }

    SQLHandler(String dbType, String user, String password) {
        this.dbType = dbType;
        this.user = user;
        this.password = password;
    }


    public void connect() throws SQLException {
        connection = DriverManager.getConnection(DataBaseFile.getUrlDB(dbType), user, password);
        stms = connection.createStatement();
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
