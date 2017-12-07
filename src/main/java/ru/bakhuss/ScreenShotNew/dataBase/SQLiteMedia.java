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
//            System.out.println(media.getTempName());
            String mediaType = media.getClass().toString();

            DataBaseFile.createDBFile();
            sqlHandler.connect();
//            String sql = "insert into Photo (name, image) values (?,?);";
            String table = media.getClass().getSimpleName();
//            String[] columns = null;

            String[] columns = switchTables(table);

            String sql = SQLConstructor.sqlPstmtInsert(table, columns);
            System.out.println("sql: " + sql);
            sqlHandler.setPstmt(sqlHandler.getConnection().prepareStatement(sql));
            Iterator<Map.Entry<Long, BufferedImage>> it = media.getMap().entrySet().iterator();
            Map.Entry<Long, BufferedImage> entry = null;
            sqlHandler.getConnection().setAutoCommit(false);
            ByteArrayOutputStream baos = null;
            long b = System.currentTimeMillis();
            while (it.hasNext()) {
                try {
                    entry = it.next();
//                    System.out.print("it: " + entry.getKey());
                    String substr = entry.getKey().toString().substring(entry.getKey().toString().length() - 7);
//                    System.out.println("it: " + substr);

                    baos = new ByteArrayOutputStream();
                    ImageIO.write(entry.getValue(), "jpg", baos);
//                    System.out.println("2: " + baos.size());
                    baos.close();
                    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

                    sqlHandler.getPstmt().setString(1, entry.getKey().toString());
                    sqlHandler.getPstmt().setBinaryStream(2, bais, baos.toByteArray().length);
//                    System.out.println("baosSize: " + baos.toByteArray().length);
                    sqlHandler.getPstmt().addBatch();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            sqlHandler.getPstmt().executeBatch();
            sqlHandler.getConnection().commit();
            sqlHandler.getConnection().setAutoCommit(true);
//            System.out.println("size: " + media.getMap().size() );
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
