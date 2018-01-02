package ru.bakhuss.ScreenShotNew.view.myFXObjects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import ru.bakhuss.ScreenShotNew.MainClass;

public class MyMenuButton extends MenuButton {

    private MenuButton mbAllMedia;
    private MenuItem allMedia, imageAllMedia, videoAllMedia, audioAllMedia;
    private MainClass mainClass = null;

    public MyMenuButton(MainClass mainClass) {
        this.mainClass = mainClass;
    }

    public MenuButton createMenuButton() {

        mbAllMedia = new MenuButton("Media");
        mbAllMedia.setPadding(new Insets(0.0, 0.0, 0.0, 10.0));
        allMedia = new MenuItem("All media");
        imageAllMedia = new MenuItem("Image");
        videoAllMedia = new MenuItem("Video");
        audioAllMedia = new MenuItem("Audio");
        mbAllMedia.getItems().addAll(allMedia, imageAllMedia, videoAllMedia, audioAllMedia);

        allMedia.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        mainClass.showAllMedia(allMedia.getText());
                    }
                }
        );

        imageAllMedia.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(event.getEventType().getName());
                        System.out.println(event.getSource().getClass().getSimpleName());
                        mainClass.showAllMedia(imageAllMedia.getText());
                    }
                }
        );

        return mbAllMedia;
    }

    public void setMainClass(MainClass mainClass) {
        this.mainClass = mainClass;
    }

}
