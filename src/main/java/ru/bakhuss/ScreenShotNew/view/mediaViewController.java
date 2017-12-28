package ru.bakhuss.ScreenShotNew.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.bakhuss.ScreenShotNew.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.dataBase.SQLHandler;
import ru.bakhuss.ScreenShotNew.dataBase.SQLite.SQLiteMedia;
import ru.bakhuss.ScreenShotNew.model.media.Image;
import ru.bakhuss.ScreenShotNew.model.media.Media;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class mediaViewController {

    private Stage dialogStage;
    private Person person;
    private static HashSet<Person> persons;
    private String mediaType;
    private ObservableList<Media> medias = FXCollections.observableArrayList();

    @FXML
    private Label lbAdd, lbDel, lbSave;
    @FXML
    private TableView<Media> tableViewMedia;
    @FXML
    private TableColumn<Media, String> mediaNameCol, mediaGroupCol, mediaTypeCol;


    @FXML
    private void initialize() {
        MyLabel.setBoldIfMouseEntered(lbAdd, 20);
        MyLabel.setBoldIfMouseEntered(lbDel, 20);
        MyLabel.setBoldIfMouseEntered(lbSave, 15);

//        if (mediaType.equals("Image")) {
//
//        }
//        sqLiteMedia.getAllMediaNameForPerson(getPerson());
    }


    public void getMediaInTableView() {
        SQLHandler sqlite = new SQLHandler(DBType.sqlite);
        SQLiteMedia sqLiteMedia = new SQLiteMedia(sqlite);
        try {
            sqlite.connect();
            ResultSet rs = sqlite.getStmt().executeQuery("select * from Image_Name");
            while (rs.next()) {
                Image img = new Image();
                img.setId(rs.getInt(1));
                img.setName(rs.getString(2));
                medias.add(img);
            }

            mediaNameCol.setCellValueFactory(param -> {
                StringProperty name = new SimpleStringProperty(param.getValue().getName());
                return name;
            });
            tableViewMedia.setItems(medias);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlite.disconnect();
        }
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
