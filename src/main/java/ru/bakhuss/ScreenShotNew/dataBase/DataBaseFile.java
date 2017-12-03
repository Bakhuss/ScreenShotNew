package ru.bakhuss.ScreenShotNew.dataBase;

public class DataBaseFile {
    static String dbInterface = "jdbc";

    private static String urlDB = "../lib/ScShdb.db";

    public static String getUrlDB(String dbType) {
        StringBuilder str = new StringBuilder();
        str.append(dbInterface).append(":").append(dbType).append(":").append(urlDB);
        System.out.println(str);
        return str.toString();

    }

}
