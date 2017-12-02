package ru.bakhuss.ScreenShotNew;

import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.media.Photo1;
import ru.bakhuss.ScreenShotNew.model.person.Person;
import ru.bakhuss.ScreenShotNew.model.person.Person1;
import ru.bakhuss.ScreenShotNew.view.mainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainClass extends Application {

    private static Stage primaryStage;
    private static Dimension Full_Screen_Size = Toolkit.getDefaultToolkit().getScreenSize();
    private static double mainMinWidth;
    private static double mainMinHeight;

    public static double getMainMinHeight() {
        return mainMinHeight;
    }

    public static void setMainMinHeight(double mainMinHeight) {
        MainClass.mainMinHeight = mainMinHeight;
    }

    public static double getMainMinWidth() {
        return mainMinWidth;
    }

    public static void setMainMinWidth(double mainMinWidth) {
        MainClass.mainMinWidth = mainMinWidth;
    }

    public MainClass() {}

    void method() {
        Media photo = new Photo1(new Person());
        Person1 person1 = new Person1();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("ScreenShot");

        show();
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/mainView.fxml"));
            AnchorPane mainView = (AnchorPane) loader.load();

            Scene scene = new Scene(mainView);
            primaryStage.setScene(scene);
            primaryStage.show();

            mainViewController controller = loader.getController();
            controller.setMainClass(this);

            setMainMinHeight(getPrimaryStage().getHeight());
            setMainMinWidth(getPrimaryStage().getWidth());
            getPrimaryStage().setMinWidth(getMainMinWidth());
            getPrimaryStage().setMinHeight(getMainMinHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMainMinSize() {
        MainClass.getPrimaryStage().setWidth( MainClass.getMainMinWidth() );
        MainClass.getPrimaryStage().setHeight( MainClass.getMainMinHeight() );
    }

    public static int getScreenMaxWidth() {
        return (int) Full_Screen_Size.getWidth();
    }

    public static int getScreenMaxHeight() {
        return (int) Full_Screen_Size.getHeight();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
