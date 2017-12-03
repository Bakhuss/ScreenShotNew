package ru.bakhuss.ScreenShotNew.dataBase;

import java.sql.*;

public class SQLHandler {

    private Connection connection;
    private Statement stms;
    private PreparedStatement pstmt;
    private DBType type;
    private String user = null;
    private String password = null;

    SQLHandler(DBType type) {
        this.type = type;
    }

    SQLHandler(DBType type, String user, String password) {
        this.type = type;
        this.user = user;
        this.password = password;
    }


    public void connect() throws SQLException {
        connection = DriverManager.getConnection(DataBaseFile.getUrlDB(type.toString()), user, password);
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
