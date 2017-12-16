package ru.bakhuss.ScreenShotNew;

import ru.bakhuss.ScreenShotNew.model.media.*;
import ru.bakhuss.ScreenShotNew.model.media.Image;
import ru.bakhuss.ScreenShotNew.model.person.Person;
import ru.bakhuss.ScreenShotNew.view.mainViewController;
import ru.bakhuss.ScreenShotNew.view.mediaViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

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

    public MainClass() {
    }

    static void method() {
        Image image = new Image();
        Media<Media> media = new Media<>();
        Media<Image> med = new Media<>();

        media.getMediaGroup().add(image);
        media.getMediaGroup().add(new Media<>());
        for ( Media m : media.getMediaGroup() ) {
            System.out.println( m.getClass().getSimpleName() );
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
//        this.primaryStage.setResizable(false);
//        this.primaryStage.setTitle();

        show();
    }

    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/mainView.fxml"));
            loader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("en")));
            AnchorPane mainView = (AnchorPane) loader.load();

            getPrimaryStage().setResizable(false);
            getPrimaryStage().setTitle(loader.getResources().getString("app.name"));

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

    public void showMediaforPerson(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/mediaView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            String name, patronymic, surname;
            name = person.getFirstName();
            patronymic = person.getPatronymic();
            surname = person.getSurname();

            dialogStage.setTitle("Media: " + name + " " + patronymic + " " + surname);
//            dialogStage.initModality(Modality.NONE);
//            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            mediaViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMainMinSize() {
        MainClass.getPrimaryStage().setWidth(MainClass.getMainMinWidth());
        MainClass.getPrimaryStage().setHeight(MainClass.getMainMinHeight());
    }

    public static int getScreenMaxWidth() {
        return (int) Full_Screen_Size.getWidth();
    }

    public static int getScreenMaxHeight() {
        return (int) Full_Screen_Size.getHeight();
    }

    public static void main(String[] args) {
        method();

        launch(args);
    }

}
