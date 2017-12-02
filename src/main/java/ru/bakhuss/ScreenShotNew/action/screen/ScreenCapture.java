package ru.bakhuss.ScreenShotNew.action.screen;

import ru.bakhuss.ScreenShotNew.MainClass;
import ru.bakhuss.ScreenShotNew.model.media.Photo;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.awt.*;
import java.util.Date;
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

    public static void getScreen(Person person) {

        long time = System.currentTimeMillis();
        int threadsCount = Runtime.getRuntime().availableProcessors()-1;

        if (threadsCount == 0) threadsCount = 1;

        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
        final Rectangle rectangle = new Rectangle(getxSize(), getySize(), getWidth(),getHeight());
        setFrames(new AtomicInteger(0));
        for (int i = 0; i < threadsCount; i++) {
            final Photo photo = new Photo(person);
            final long t = System.currentTimeMillis();
            final int w = i;
            executor.execute(new Runnable() {
                public void run() {
                    do {
                        try {
                            photo.addPhoto(System.currentTimeMillis(), new Robot().createScreenCapture(rectangle));
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                    } while (System.currentTimeMillis() - t < timeScreen * 1000);
                    System.out.print("run" + w + " - " + photo.size() + "; ");
                    getFrames().addAndGet(photo.size());
                }
            });
        }
        executor.shutdown();

        while (!executor.isTerminated()) {}

        System.out.println("\ntime: " + (System.currentTimeMillis() - time) );
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
