package ru.bakhuss.ScreenShotNew.dataBase;

import ru.bakhuss.ScreenShotNew.dataBase.sqlQuery.SQLConstructor;
import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.media.Photo;

import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLiteMedia implements MediaRepository {

    SQLHandler sqlHandler;

    public SQLiteMedia() {
        sqlHandler = new SQLHandler(DBType.sqlite);
    }

    @Override
    public void set(Media media) {
        try {
            DataBaseFile.createDBFile();
            sqlHandler.connect();
            String table = media.getClass().getSimpleName();
            String[] columns = switchTables(table);

//            String sql = SQLConstructor.sqlPstmtInsert(table, columns);
//            System.out.println("sql: " + sql);
            Iterator<Map.Entry<Long, BufferedImage>> it = media.getMap().entrySet().iterator();
            Map.Entry<Long, BufferedImage> entry = null;
            ByteArrayOutputStream baos = null;
            long b = System.currentTimeMillis();
            while (it.hasNext()) {
                try {
                    entry = it.next();
                    baos = new ByteArrayOutputStream();
                    ImageIO.write(entry.getValue(), "jpg", baos);
                    baos.close();
                    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

                    String query = "insert into Photo (date_in, image) values (?,?);";
                    sqlHandler.setPstmt(sqlHandler.getConnection().prepareStatement(query));
                    sqlHandler.getPstmt().setString(1, entry.getKey().toString());
                    sqlHandler.getPstmt().setBinaryStream(2, bais, baos.toByteArray().length);
                    sqlHandler.getPstmt().execute();
                    query = "select id from Photo where rowid = last_insert_rowid();";
                    ResultSet rs = sqlHandler.getStmt().executeQuery(query);
                    rs.next();
                    int photoId = rs.getInt(1);
                    rs.close();
                    query = "insert into Photo_Name (photo_group_id, photo_id, name) values (?,?,?);";
                    sqlHandler.setPstmt(sqlHandler.getConnection().prepareStatement(query));
                    sqlHandler.getPstmt().setInt(1,media.getGroupNameId());
                    sqlHandler.getPstmt().setInt(2, photoId);
                    sqlHandler.getPstmt().setString(3, String.valueOf(entry.getKey().toString()));
                    sqlHandler.getPstmt().execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("sqlTime: " + (System.currentTimeMillis()-b));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlHandler.disconnect();
        }
    }

    private String[] switchTables(String table) {
        String[] columns = null;
        switch (table) {
            case "Photo":
                columns = new String[2];
                columns[0] = "date_in";
                columns[1] = "image";
                break;
            default:
                System.out.println("SetSQL: Не указана таблица для заполнения!");
        }
        return columns;
    }

    public int setGroupName(String groupName) {
        try {
            String sqlIns = "insert into Photo_Group (name, auto_screen) values ('" + groupName + "', 1);";
            String sqlSel = "select id from Photo_Group where rowid = last_insert_rowid();";
            sqlHandler.connect();
            sqlHandler.getStmt().execute(sqlIns);
            ResultSet rs = sqlHandler.getStmt().executeQuery(sqlSel);
            rs.next();
            System.out.println("rs: " + rs.getInt(1));
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: setGroupName");
        } finally {
            sqlHandler.disconnect();
        }
        return 0;
    }

    @Override
    public Media get() {

        return null;
    }

    @Override
    public void update(Media media) {

    }

    @Override
    public void remove(Media media) {

    }
}
