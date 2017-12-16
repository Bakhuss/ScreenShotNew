package ru.bakhuss.ScreenShotNew.action.screen;

import ru.bakhuss.ScreenShotNew.MainClass;
import ru.bakhuss.ScreenShotNew.dataBase.DataBaseFile;
import ru.bakhuss.ScreenShotNew.dataBase.SQLiteMedia;
import ru.bakhuss.ScreenShotNew.model.media.Image;
import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private static final String dateFormStr = "dd.MM.yyyy-HH:mm:ss.SSS-z";
    private static final TimeZone TZ_utc = TimeZone.getTimeZone("UTC");
    private static final int TIMEZONE_OFFSET = TimeZone.getDefault().getOffset(new Date().getTime());

    public static void getScreen(Person person) {
        System.out.println("dbFile: " + DataBaseFile.getDBFile());
        final Rectangle rectangle = new Rectangle(getxSize(), getySize(), getWidth(), getHeight());

        int threadsCount = Runtime.getRuntime().availableProcessors() - 1;
        dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormStr);
        dateFormat.setTimeZone(TZ_utc);
        String date = dateFormat.format(dateNow);
        System.out.println("date: " + date);
        SimpleDateFormat saveDateUTC = new SimpleDateFormat();
        saveDateUTC.setTimeZone(TZ_utc);
        Date d = new Date();
        System.out.println(d);
        System.out.println(saveDateUTC.format(d));

        if (threadsCount == 0) threadsCount = 1;
        System.out.println("treads: " + threadsCount);

        Media<Image>[] medias = new Media[threadsCount];

        SQLiteMedia groupName = new SQLiteMedia();
        groupNameId = groupName.setGroupName(date);
        System.out.println("groupNameId: " + groupNameId);

        SQLiteMedia[] sqLiteMedia = new SQLiteMedia[threadsCount];
        Media[] media = new Image[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            sqLiteMedia[i] = new SQLiteMedia();
            medias[i] = new Image();
            medias[i].setGroupNameId(groupNameId);
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
//                            media[w].getMap().put((System.currentTimeMillis() - TIMEZONE_OFFSET), new Robot().createScreenCapture(rectangle));

                            medias[w].getMediaGroup().add(new Image( (System.currentTimeMillis() - TIMEZONE_OFFSET), new Robot().createScreenCapture(rectangle) ));
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    } while (System.currentTimeMillis() - time < timeScreen * 1000);
                    System.out.println("\ntime: " + (System.currentTimeMillis() - time) + " | " + w + " " + medias[w].getMediaGroup().size());
                    sqLiteMedia[w].set(medias[w]);
                    getFrames().addAndGet(medias[w].getMediaGroup().size());
                    medias[w].getMediaGroup().clear();
                }
            });

        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("photoSize: " + getFrames().get());

//        sql.remove(m);
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
