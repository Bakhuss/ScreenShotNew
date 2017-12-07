package ru.bakhuss.ScreenShotNew.action.screen;

import ru.bakhuss.ScreenShotNew.MainClass;
import ru.bakhuss.ScreenShotNew.dataBase.DataBaseFile;
import ru.bakhuss.ScreenShotNew.dataBase.SQLiteMedia;
import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.media.Photo;
import ru.bakhuss.ScreenShotNew.model.person.Person;
import sun.awt.image.BufferedImageDevice;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenCapture {

    private static int width = MainClass.getScreenMaxWidth();
    private static int height = MainClass.getScreenMaxHeight();
    private static int xSize = 0;
    private static int ySize = 0;
    private static Date dateNow = null;
    private static int timeScreen = 1;
    private static int countFrames = 0;
    private static AtomicInteger frames;
    private static int groupNameId = 0;

    public static void getScreen(Person person) {
        System.out.println("dbFile: " + DataBaseFile.getDBFile());
        final Rectangle rectangle = new Rectangle(getxSize(), getySize(), getWidth(),getHeight());

        int threadsCount = Runtime.getRuntime().availableProcessors()-1;
        dateNow = new Date();
        System.out.println("date: " + dateNow);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-kk:mm:ss.SS");
        String date = dateFormat.format(dateNow);
        System.out.println("date: " + date);

        if (threadsCount == 0) threadsCount = 1;
        System.out.println("treads: " + threadsCount);

        SQLiteMedia photoGroup = new SQLiteMedia();
        groupNameId = photoGroup.setGroupName(date);
        System.out.println("groupNameId: " + groupNameId);

        SQLiteMedia[] sqLiteMedia = new SQLiteMedia[threadsCount];
        Media[] media = new Photo[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            sqLiteMedia[i] = new SQLiteMedia();
            media[i] = new Photo(date, groupNameId);
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        setFrames(new AtomicInteger(0));
        final long time = System.currentTimeMillis();
        for (int i = 0; i < threadsCount; i++) {
            final int w = i;
            executor.execute(new Runnable() {
                public void run() {
                    do {
                        try {
                            media[w].getMap().put(System.currentTimeMillis(), new Robot().createScreenCapture(rectangle));
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    } while (System.currentTimeMillis() - time < timeScreen * 1000);
                    System.out.println("\ntime: " + (System.currentTimeMillis() - time) + " | " + w + " " + media[w].getMap().size() );
                    sqLiteMedia[w].set(media[w]);
                    getFrames().addAndGet(media[w].getMap().size());
                    media[w].getMap().clear();
                }
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()) {}
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
}
