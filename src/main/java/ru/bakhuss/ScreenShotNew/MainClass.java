package ru.bakhuss.ScreenShotNew;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.WindowEvent;
import ru.bakhuss.ScreenShotNew.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.dataBase.SQLHandler;
import ru.bakhuss.ScreenShotNew.dataBase.SQLite.SQLiteMedia;
import ru.bakhuss.ScreenShotNew.model.media.*;
import ru.bakhuss.ScreenShotNew.model.media.Image;
import ru.bakhuss.ScreenShotNew.model.person.Person;
import ru.bakhuss.ScreenShotNew.view.myFXObjects.MyMenuButton;
import ru.bakhuss.ScreenShotNew.view.mainViewController;
import ru.bakhuss.ScreenShotNew.view.mediaViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Locale;
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
        SQLHandler sqlite = new SQLHandler(DBType.sqlite);
        SQLiteMedia sqLiteMedia = new SQLiteMedia(sqlite);


        MediaGroup<Media> media = new MediaGroup<>(false);
        media.getMediaList().add(new Image());
        media.getMediaList().add(new Audio());
        media.getMediaList().add(new Video());
        media.getMediaList().add(new Image());
        Person person = new Person();
        person.newPersonMediaList();
        System.out.println(person.getPersonMediaList().size());
        person.getPersonMediaList().add(media);
        person.getPersonMediaList().add(new Image());
        System.out.println(person.getPersonMediaList().size());

        MediaInterface interf = new MediaGroup(false);
        System.out.println("interfClass: " + interf.getClass().getSimpleName());



        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());
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
            MyMenuButton myMenuButton = new MyMenuButton(this);
            HBox hBox = (HBox) page.getChildren().get(0);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.getChildren().add(myMenuButton.createMenuButton());
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            dialogStage.setOnCloseRequest(
                    new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            System.out.println("close media for Person " + person.getFullName());
                        }
                    }
            );
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllMedia(String mediaType) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClass.class.getResource("view/mediaView.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();

            dialogStage.setTitle(mediaType);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            mediaViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.setOnCloseRequest(
                    new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            System.out.println("close AllMedia " + mediaType);
                        }
                    }
            );


            dialogStage.show();


            switch (mediaType) {
                case "All media":
                    System.out.println("all media");
                    controller.setMainClass(this);
                    controller.getAllMediaInTableView();
                    break;
                case "Image":
                    TableView tView = (TableView) pane.getChildren().get(1);
                    tView.getColumns().remove(2);
                    controller.getImageInTableView();
                    break;
                case "Video":
                    System.out.println("Video");
                    break;
                case "Audio":
                    System.out.println("Audio");
                    break;
                default:
                    break;
            }


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
