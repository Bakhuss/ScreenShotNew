package ru.bakhuss.ScreenShotNew.dataBase;

import java.sql.*;

public class SQLHandler {

    private Connection connection;
    private Statement stmt;
    private PreparedStatement pstmt;
    private DBType type;
    private String user = null;
    private String password = null;

    public SQLHandler(DBType type) {
        this.type = type;
    }

    SQLHandler(DBType type, String user, String password) {
        this.type = type;
        this.user = user;
        this.password = password;
    }


    public void connect() throws SQLException {
        connection = DriverManager.getConnection(DataBaseFile.getUrlDB(type.toString()), user, password);
        stmt = connection.createStatement();
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStmt() {
        return stmt;
    }

    public PreparedStatement getPstmt() {
        return pstmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public void setPstmt(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }
}
