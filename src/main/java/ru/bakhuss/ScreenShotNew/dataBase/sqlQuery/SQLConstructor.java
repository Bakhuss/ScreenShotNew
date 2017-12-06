package ru.bakhuss.ScreenShotNew.dataBase.sqlQuery;

public class SQLConstructor {

    public static String sqlPstmtInsert(String table, String... columns) {
        StringBuilder strColumns, strValues;

        strColumns = new StringBuilder("insert into " + table + " (");
        strValues = new StringBuilder(") values (");

        for ( String s : columns ) {
            strColumns.append(s).append(",");
            strValues.append("?,");
        }

        strColumns.deleteCharAt(strColumns.length()-1);
        strValues.deleteCharAt(strValues.length()-1);

        return strColumns.append(strValues).append(");").toString();
    }
}
