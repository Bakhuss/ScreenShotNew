package ru.bakhuss.ScreenShotNew.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ru.bakhuss.ScreenShotNew.dataBase.SQLite.SQLiteMedia;
import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.util.ArrayList;
import java.util.HashSet;

public class mediaViewController {

    private Stage dialogStage;
    private Person person;
    private static HashSet<Person> persons;

    @FXML
    private TableView<Media> tableViewMediaForPerson;
    @FXML
    private TableColumn<Media, String> mediaName;
    @FXML
    private TableColumn<Media, String> mediaGroup;
    @FXML
    private TableColumn<Media, String> mediaType;

    @FXML
    private void initialize() {
        SQLiteMedia sqLiteMedia = new SQLiteMedia();
//        sqLiteMedia.getAllMediaNameForPerson(getPerson());
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static HashSet<Person> getPersons() {
        return persons;
    }

    public static void setPersons() {
        mediaViewController.persons = new HashSet<>();
    }
}
