package ru.bakhuss.ScreenShotNew.dataBase;

public class DataBaseFile {

    private static String urlDB = "../../lib/ScShdb.db";

    public static String getUrlDB(String dbType) {
        return dbType.concat(urlDB);
    }
}
