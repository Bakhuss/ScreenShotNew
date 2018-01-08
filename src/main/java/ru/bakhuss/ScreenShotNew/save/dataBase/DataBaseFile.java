package ru.bakhuss.ScreenShotNew.save.dataBase;

import javax.xml.crypto.Data;
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseFile {
    static String dbInterface = "jdbc";
    private static String urlDB = "../lib/ScShdb.db";
//    private static String urlDB = "ScShdb.db";
    private static DBType dbType;

    public static String getUrlDB(DBType dbType) {
        DataBaseFile.dbType = dbType;
        String typeDB = dbType.toString();
        StringBuilder str = new StringBuilder();
        str.append(dbInterface).append(":").append(typeDB).append(":").append(urlDB);
        return str.toString();
    }

    public static void createDBFile() {
        String sql = "CREATE TABLE IF NOT EXISTS Image (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                  NOT NULL,\n" +
                "    groupName  TEXT    NOT NULL,\n" +
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
        String sql = "CREATE TABLE IF NOT EXISTS Audio (\n" +
                "    id      INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    UNIQUE\n" +
                "                    NOT NULL,\n" +
                "    date_in TEXT    NOT NULL,\n" +
                "    audio   BLOB    NOT NULL\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS Image (\n" +
                "    id      INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    NOT NULL\n" +
                "                    UNIQUE,\n" +
                "    date_in TEXT    NOT NULL,\n" +
                "    image   BLOB    NOT NULL\n" +
                ");";
        stmt.execute(sql);
        sql = "CREATE TRIGGER after_insert\n" +
                "         AFTER INSERT\n" +
                "            ON Image\n" +
                "BEGIN\n" +
                "    INSERT INTO Image_Temp (\n" +
                "                               image_id\n" +
                "                           )\n" +
                "                           VALUES (\n" +
                "                               new.id\n" +
                "                           );\n" +
                "END;";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS Video (\n" +
                "    id      INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    UNIQUE\n" +
                "                    NOT NULL,\n" +
                "    date_in TEXT    NOT NULL,\n" +
                "    video   BLOB    NOT NULL\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Audio_Name (\n" +
                "    audio_id INTEGER REFERENCES Audio (id) \n" +
                "                     NOT NULL,\n" +
                "    groupName     TEXT    NOT NULL\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS Image_Name (\n" +
                "    id       INTEGER PRIMARY KEY\n" +
                "                     UNIQUE\n" +
                "                     NOT NULL,\n" +
                "    image_id INTEGER REFERENCES Image (id) ON DELETE CASCADE\n" +
                "                                           ON UPDATE CASCADE\n" +
                "                     NOT NULL,\n" +
                "    name     TEXT    NOT NULL\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Video_Name (\n" +
                "    video_id INTEGER REFERENCES Video (id) \n" +
                "                     NOT NULL,\n" +
                "    groupName     TEXT    NOT NULL\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE First_Name (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       UNIQUE\n" +
                "                       NOT NULL,\n" +
                "    first_name TEXT    UNIQUE\n" +
                "                       NOT NULL\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Patronymic (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       UNIQUE\n" +
                "                       NOT NULL,\n" +
                "    patronymic TEXT    UNIQUE\n" +
                "                       NOT NULL\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Surname (\n" +
                "    id      INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                    NOT NULL\n" +
                "                    UNIQUE,\n" +
                "    surname TEXT    UNIQUE\n" +
                "                    NOT NULL\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Full_Name (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       UNIQUE\n" +
                "                       NOT NULL,\n" +
                "    surname    TEXT,\n" +
                "    first_name TEXT,\n" +
                "    patronymic TEXT\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Group_Name (\n" +
                "    id             INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                           UNIQUE\n" +
                "                           NOT NULL,\n" +
                "    name           TEXT    NOT NULL,\n" +
                "    date_in        TEXT,\n" +
                "    auto_screen    BOOLEAN NOT NULL,\n" +
                "    elements_count INTEGER NOT NULL\n" +
                "                           DEFAULT (0) \n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Group_All_Media (\n" +
                "    group_name INTEGER NOT NULL\n" +
                "                       REFERENCES Group_Name (id),\n" +
                "    any_media  INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (\n" +
                "        any_media\n" +
                "    )\n" +
                "    REFERENCES Image_Name (id) ON DELETE CASCADE\n" +
                "                               ON UPDATE CASCADE,\n" +
                "    FOREIGN KEY (\n" +
                "        any_media\n" +
                "    )\n" +
                "    REFERENCES Audio_Name (audio_id) ON DELETE CASCADE,\n" +
                "    FOREIGN KEY (\n" +
                "        any_media\n" +
                "    )\n" +
                "    REFERENCES Video_Name (video_id) ON DELETE CASCADE,\n" +
                "    FOREIGN KEY (\n" +
                "        any_media\n" +
                "    )\n" +
                "    REFERENCES Group_Name (id) ON DELETE CASCADE\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Person (\n" +
                "    id               INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                             UNIQUE\n" +
                "                             NOT NULL,\n" +
                "    full_name_id     INTEGER REFERENCES Full_Name (id) \n" +
                "                             NOT NULL,\n" +
                "    personal_data_id INTEGER REFERENCES Personal_Data (id) \n" +
                "                             UNIQUE\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Personal_Data (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       UNIQUE\n" +
                "                       NOT NULL,\n" +
                "    birthDay   TEXT,\n" +
                "    birthPlace TEXT,\n" +
                "    deathDay   TEXT,\n" +
                "    deathPlace TEXT,\n" +
                "    height     TEXT,\n" +
                "    eyeColor   TEXT,\n" +
                "    hairColor  TEXT\n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Person_AND_Media (\n" +
                "    person_id INTEGER REFERENCES Person (id) \n" +
                "                      NOT NULL,\n" +
                "    any_media INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (\n" +
                "        any_media\n" +
                "    )\n" +
                "    REFERENCES Audio_Name (audio_id),\n" +
                "    FOREIGN KEY (\n" +
                "        any_media\n" +
                "    )\n" +
                "    REFERENCES Image_Name (image_id),\n" +
                "    FOREIGN KEY (\n" +
                "        any_media\n" +
                "    )\n" +
                "    REFERENCES Video_Name (video_id),\n" +
                "    FOREIGN KEY (\n" +
                "        any_media\n" +
                "    )\n" +
                "    REFERENCES Group_Name (id) \n" +
                ");";
        stmt.execute(sql);

        sql = "CREATE TABLE Media (\n" +
                "    id           INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                         UNIQUE\n" +
                "                         NOT NULL,\n" +
                "    all_media_id INTEGER,\n" +
                "    FOREIGN KEY (\n" +
                "        all_media_id\n" +
                "    )\n" +
                "    REFERENCES Audio_Name (audio_id),\n" +
                "    FOREIGN KEY (\n" +
                "        all_media_id\n" +
                "    )\n" +
                "    REFERENCES Group_Name (id),\n" +
                "    FOREIGN KEY (\n" +
                "        all_media_id\n" +
                "    )\n" +
                "    REFERENCES Image_Name (image_id),\n" +
                "    FOREIGN KEY (\n" +
                "        all_media_id\n" +
                "    )\n" +
                "    REFERENCES Video_Name (video_id) \n" +
                ");";
//        stmt.execute(sql);
    }

}
