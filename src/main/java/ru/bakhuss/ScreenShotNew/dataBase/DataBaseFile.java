package ru.bakhuss.ScreenShotNew.dataBase;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseFile {
    static String dbInterface = "jdbc";
    private static String urlDB = "../lib/ScShdb.db";
//    private static String urlDB = "ScShdb.db";

    public static String getUrlDB(String dbType) {
        StringBuilder str = new StringBuilder();
        str.append(dbInterface).append(":").append(dbType).append(":").append(urlDB);
        return str.toString();
    }

    public static void createDBFile() {
        String sql = "CREATE TABLE IF NOT EXISTS Photo (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                  NOT NULL,\n" +
                "    name  TEXT    NOT NULL,\n" +
                "    image BLOB\n" +
                ");";
        SQLHandler handler = new SQLHandler(DBType.sqlite);

        try {
            handler.connect();
            handler.getStmt().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            handler.disconnect();
        }

    }

    public static void setDBFile(File dbFile) {
        DataBaseFile.urlDB = dbFile.getAbsolutePath();
    }

    public static String getDBFile() {
        return DataBaseFile.urlDB;
    }

    public static void createDBStructure(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Photo (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                  NOT NULL,\n" +
                "    name  TEXT    NOT NULL,\n" +
                "    image BLOB\n" +
                ");";
        stmt.execute(sql);
    }

}
