package ru.bakhuss.ScreenShotNew.view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class MyLabel extends Label {

    public static void setBoldIfMouseEntered(Label label, double size) {
        label.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        label.setFont(new Font("System BOLD", size));
                    }
                }
        );
        label.setOnMouseExited(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        label.setFont(new Font(size));
                    }
                }
        );
    }

}
