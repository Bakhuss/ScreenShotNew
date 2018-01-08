package ru.bakhuss.ScreenShotNew.action.screen;

import ru.bakhuss.ScreenShotNew.MainClass;
import ru.bakhuss.ScreenShotNew.save.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.save.dataBase.DataBaseFile;
import ru.bakhuss.ScreenShotNew.save.dataBase.SQLHandler;
import ru.bakhuss.ScreenShotNew.save.dataBase.SQLite.SQLiteMedia;
import ru.bakhuss.ScreenShotNew.model.MyDate;
import ru.bakhuss.ScreenShotNew.model.media.Image;
import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.media.MediaGroup;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import javax.management.relation.RelationSupport;
import java.awt.*;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenCapture {

    private static int width = MainClass.getScreenMaxWidth();
    private static int height = MainClass.getScreenMaxHeight();
    private static int xSize = 0;
    private static int ySize = 0;
    private static int timeScreen = 1;
    private static int countFrames = 0;
    private static AtomicInteger frames;
    private static Long groupNameId = null;

    private static Person person = null;
    private static MediaGroup mediaGroup = null;


    public static void getScreen() {
        System.out.println("dbFile: " + DataBaseFile.getDBFile());
        final Rectangle rectangle = new Rectangle(getxSize(), getySize(), getWidth(), getHeight());

        int threadsCount = Runtime.getRuntime().availableProcessors() - 1;

        SQLHandler sqlite = new SQLHandler(DBType.sqlite);
        SQLiteMedia sqLiteMedia = new SQLiteMedia(sqlite);

        if (threadsCount == 0) threadsCount = 1;
        System.out.println("treads: " + threadsCount);

        MediaGroup<Image>[] medias = new MediaGroup[threadsCount];


        try {
            sqlite.connect();
            sqlite.getConnection().setAutoCommit(false);
            String imgTemp = "CREATE TABLE IF NOT EXISTS Image_Temp (\n" +
                    "    group_name_id INTEGER,\n" +
                    "    image_id      INTEGER\n" +
                    ");";
            sqlite.getStmt().execute(imgTemp);

            if (mediaGroup != null) {
                if (mediaGroup.getId() != 0) {
                    groupNameId = mediaGroup.getId();
                } else groupNameId = sqLiteMedia.setGroupName(mediaGroup.getName());
            } else groupNameId = sqLiteMedia.setGroupName(MyDate.dateNowUTCString());

            sqlite.getStmt().execute("insert into Image_Temp (group_name_id) values (" + groupNameId + ")");

            for (int i = 0; i < threadsCount; i++) {
                medias[i] = new MediaGroup<>(true);
                medias[i].setId(groupNameId);
            }

            System.out.println("groupNameId = " + groupNameId);

            ExecutorService executor = Executors.newFixedThreadPool(2);
            setFrames(new AtomicInteger(0));
            final long time = System.currentTimeMillis();

            for (int i = 0; i < threadsCount; i++) {
                final int w = i;
                executor.execute(new Runnable() {
                    public void run() {
                        do {
                            try {
                                medias[w].getMediaList().add(new Image(MyDate.dateNowUTClong(), new Robot().createScreenCapture(rectangle)));
                            } catch (AWTException e) {
                                e.printStackTrace();
                            }
                        } while (System.currentTimeMillis() - time < timeScreen * 1000);
                        System.out.println("\ntime: " + (System.currentTimeMillis() - time) + " | " + w + " " + medias[w].getMediaList().size());
                        getFrames().addAndGet(medias[w].getMediaList().size());
                        sqLiteMedia.set(medias[w]);
                        System.out.println("w: " + medias[w].getMediaList().size());
                        medias[w].getMediaList().clear();
                    }
                });

            }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
            sqlite.getPstmt().executeBatch();

            String query = "insert into Image_Name (image_id, name) " +
                    "select id, date_in from Image where id in ( select image_id from Image_Temp);";
            sqlite.getStmt().execute(query);

            query = "insert into Group_All_Media (group_name, any_media) " +
                    "select " + groupNameId + ", image_id from Image_Temp where image_id IS NOT NULL;";

            query = "insert into Group_All_Media (group_name, any_media) " +
                    "select (select group_name_id from Image_Temp where group_name_id is not null), " +
                    "image_id from Image_Temp where image_id is not null;";

            query = "insert into Group_All_Media (group_name, any_media) " +
                    "select (select group_name_id from Image_Temp where group_name_id is not null), " +
                    "id from Image_Name where image_id in ( select image_id from Image_Temp );";

            sqlite.getStmt().execute(query);
            sqlite.getStmt().execute("DROP TABLE IF EXISTS Image_Temp;");
            sqlite.getStmt().execute("update Group_Name set elements_count = " + getFrames() + " where id = " + groupNameId + ";");

            sqlite.getConnection().commit();
            sqlite.getConnection().setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlite.disconnect();
        }

        System.out.println("photoSize: " + getFrames().get());

    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        ScreenCapture.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        ScreenCapture.height = height;
    }

    public static int getxSize() {
        return xSize;
    }

    public static void setxSize(int xSize) {
        ScreenCapture.xSize = xSize;
    }

    public static int getySize() {
        return ySize;
    }

    public static void setySize(int ySize) {
        ScreenCapture.ySize = ySize;
    }

    public static int getTimeScreen() {
        return timeScreen;
    }

    public static void setTimeScreen(int timeScreen) {
        ScreenCapture.timeScreen = timeScreen;
    }

    public static AtomicInteger getFrames() {
        return frames;
    }

    public static void setFrames(AtomicInteger frames) {
        ScreenCapture.frames = frames;
    }

    private static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        ScreenCapture.person = person;
    }

    private static MediaGroup getMediaGroup() {
        return mediaGroup;
    }

    public static void setMediaGroup(MediaGroup mediaGroup) {
        ScreenCapture.mediaGroup = mediaGroup;
    }

/*
    public static int getTimezoneOffset() {
        return TIMEZONE_OFFSET;
    }
*/
}
