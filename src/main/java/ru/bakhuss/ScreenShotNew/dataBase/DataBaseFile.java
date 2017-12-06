package ru.bakhuss.ScreenShotNew.dataBase;

import java.sql.SQLException;

public class DataBaseFile {
    static String dbInterface = "jdbc";

//    private static String urlDB = "../lib/ScShdb.db";
private static String urlDB = "ScShdb.db";

    public static String getUrlDB(String dbType) {
        StringBuilder str = new StringBuilder();
        str.append(dbInterface).append(":").append(dbType).append(":").append(urlDB);
        System.out.println(str);
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

}
