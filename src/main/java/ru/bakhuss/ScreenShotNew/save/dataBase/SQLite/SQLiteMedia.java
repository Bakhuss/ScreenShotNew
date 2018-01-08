package ru.bakhuss.ScreenShotNew.save.dataBase.SQLite;

import ru.bakhuss.ScreenShotNew.action.screen.ScreenCapture;
import ru.bakhuss.ScreenShotNew.save.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.save.dataBase.MediaRepo;
import ru.bakhuss.ScreenShotNew.save.dataBase.SQLHandler;
import ru.bakhuss.ScreenShotNew.model.MyDate;
import ru.bakhuss.ScreenShotNew.model.media.Image;
import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.media.MediaGroup;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import javax.imageio.ImageIO;
import java.io.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLiteMedia implements MediaRepo {

    Object obj = new Object();

    SQLHandler sqlHandler;

    public SQLiteMedia() {
        sqlHandler = new SQLHandler(DBType.sqlite);
    }

    public SQLiteMedia(SQLHandler sqlHandler) {
        this.sqlHandler = sqlHandler;
    }

    @Override
    public void set(Media media) {
    }

    @Override
    public void set(Media... media) {
    }

    @Override
    public synchronized void set(MediaGroup mediaGroup) {
        Array array = null;
        try {
            String table = mediaGroup.getClass().getSimpleName();

            Iterator<Image> it = mediaGroup.getMediaList().iterator();
            Image entry = null;
            ByteArrayOutputStream baos = null;
            long b = System.currentTimeMillis();
            while (it.hasNext()) {
                try {
                    entry = it.next();
                    baos = new ByteArrayOutputStream();
                    ImageIO.write(entry.getImage(), "jpg", baos);
                    baos.close();
                    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

                    String query = "insert into Image (date_in, image) values (?,?);";
                    if (sqlHandler.getPstmt() == null) {
                        System.out.println("Pstmt is null");
                        sqlHandler.setPstmt(sqlHandler.getConnection().prepareStatement(query));
                    }
                    sqlHandler.getPstmt().setString(1, entry.getDateIn().toString());
                    sqlHandler.getPstmt().setBinaryStream(2, bais, baos.toByteArray().length);
                    sqlHandler.getPstmt().addBatch();


//                    query = "select id from Image where rowid = last_insert_rowid();";
//                    ResultSet rs = sqlHandler.getStmt().executeQuery(query);
//                    rs.next();
//                    int imageId = rs.getInt(1);
//                    System.out.println("imageId: " + imageId);
//                    rs.close();
//                    System.out.print(imageId + " ");
//                    query = "insert into Image_Name (image_id, name) values (?,?);";
//                    sqlHandler.setPstmt(sqlHandler.getConnection().prepareStatement(query));
//                    sqlHandler.getPstmt().setInt(1, imageId);
//                    sqlHandler.getPstmt().setString(2, entry.getDateIn().toString());
//                    sqlHandler.getPstmt().execute();
//                    query = "insert into Group_All_Media (group_name, any_media) values (" + mediaGroup.getGroupNameId() + "," + imageId + ");";
//                    sqlHandler.getStmt().execute(query);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("sqlTime: " + (System.currentTimeMillis() - b));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }

    }

    private String[] switchTables(String table) {
        String[] columns = null;
        switch (table) {
            case "Image":
                columns = new String[2];
                columns[0] = "date_in";
                columns[1] = "image";
                break;
            default:
                System.out.println("SetSQL: Не указана таблица для заполнения!");
        }
        return columns;
    }

    public Long setGroupName(String groupName) {
        try {
//            long date_in = System.currentTimeMillis() - ScreenCapture.getTimezoneOffset();
            long date_in = MyDate.dateNowUTClong();
            String sqlIns = "insert into Group_Name (name, date_in, auto_screen) values ('" + groupName + "'," + date_in + ", 1);";
            String sqlSel = "select id from Group_Name where rowid = last_insert_rowid();";
//            sqlHandler.connect();
            sqlHandler.getStmt().execute(sqlIns);
            ResultSet rs = sqlHandler.getStmt().executeQuery(sqlSel);
            rs.next();
            System.out.println("rs: " + rs.getInt(1));
            return Long.valueOf(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: setGroupName");
        } finally {
//            sqlHandler.disconnect();
        }
        return null;
    }

    @Override
    public Media get() {

        return null;
    }

    public ArrayList<Media> getAllMediaNameForPerson(Person person) {
        ArrayList<Media> medias = new ArrayList<>();
        try {
            sqlHandler.connect();
            String query = "select Image_Name.groupName as Image, Audio_Name.groupName as Audio, Video_Name.groupName as Video, Group_Name.groupName as 'Group', auto_screen from Person_AND_Media\n" +
                    "    inner join Image_Name on Person_AND_Media.any_media = Image_Name.image_id\n" +
                    "    inner join Audio_Name on Person_AND_Media.any_media = Audio_Name.audio_id\n" +
                    "    inner join Video_Name on Person_AND_Media.any_media = Video_Name.video_id\n" +
                    "    inner join Group_Name on Person_AND_Media.any_media = Group_Name.id\n" +
                    "where person_id = " + person.getId() + ";";
            ResultSet rs = sqlHandler.getStmt().executeQuery(query);
            while (rs.next()) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlHandler.disconnect();
        }
        return medias;
    }

    @Override
    public ArrayList<Media> getAll() {
        return null;
    }

    @Override
    public void update(Media media) {

    }

    @Override
    public void remove(Media media) {
        try {
            sqlHandler.connect();
//            int a = sqlHandler.getStmt().executeUpdate("delete from Image where id in (select any_media from Group_All_Media where group_name = " + media.getGroupNameId() + ");");
//            System.out.println("delete: " + a);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: remove media");
        } finally {
            sqlHandler.disconnect();
        }
    }

}
