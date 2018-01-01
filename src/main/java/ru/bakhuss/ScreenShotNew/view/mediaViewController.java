package ru.bakhuss.ScreenShotNew.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Builder;
import javafx.util.Callback;
import ru.bakhuss.ScreenShotNew.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.dataBase.SQLHandler;
import ru.bakhuss.ScreenShotNew.dataBase.SQLite.SQLiteMedia;
import ru.bakhuss.ScreenShotNew.model.media.*;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.lang.reflect.ParameterizedType;
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
    private ObservableList<MediaInterface> mediaGroups = FXCollections.observableArrayList();
    private int count = 0;

    @FXML
    private Label lbAdd, lbDel, lbSave;
//    @FXML
//    private TableView<Media> tableViewMedia;

    @FXML
    private TableView<MediaInterface> tableViewMedia;

    @FXML
    private TableColumn<MediaInterface, String> mediaNameCol, mediaGroupCol, mediaTypeCol;


    @FXML
    private void initialize() {
        MyLabel.setBoldIfMouseEntered(lbAdd, 20);
        MyLabel.setBoldIfMouseEntered(lbDel, 20);
        MyLabel.setBoldIfMouseEntered(lbSave, 15);
        mediaNameCol.setStyle("-fx-alignment: CENTER;");
        mediaGroupCol.setStyle("-fx-alignment: CENTER;");
        mediaTypeCol.setStyle("-fx-alignment: CENTER;");
    }


    public void getImageInTableView() {
        SQLHandler sqlite = new SQLHandler(DBType.sqlite);

        try {
            sqlite.connect();
            ResultSet rs = sqlite.getStmt().executeQuery("select * from Image_Name");
            while (rs.next()) {
//                MediaGroup<Image> media = new MediaGroup<>(false);
                Image img = new Image();
//                Image img = new Image();
                img.setId(rs.getInt(1));
                img.setName(rs.getString(2));

//                ((MediaGroup<Image>)media).getMediaList().add(img);
//                media.getMediaList().add(img);
                mediaGroups.add(img);
            }
            System.out.println("mediaGroups: " + mediaGroups.size());

            mediaNameCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaInterface, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaInterface, String> param) {
                            return new SimpleStringProperty(
//                                    ((MediaGroup<Image>)param.getValue()).getMediaList().get(0).getName()
                                    ((Image)param.getValue()).getName()
//                                    ((Image) param.getValue().getMediaList().get(0)).getName()
                            );
                        }
                    }
            );
            tableViewMedia.setItems(mediaGroups);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlite.disconnect();
        }
    }

    public void getAllMediaInTableView() {
        SQLHandler sqlite = new SQLHandler(DBType.sqlite);

        try {
            sqlite.connect();
            ResultSet rs = sqlite.getStmt().executeQuery("select * from Group_Name;");
            while (rs.next()) {
                boolean autoscreen = false;
                if (rs.getInt(3) == 1) autoscreen = true;
                MediaInterface media = new MediaGroup<>(autoscreen);
                ((MediaGroup)media).setGroupNameId(rs.getInt(1));
                ((MediaGroup)media).setGroupName(rs.getString(2));

//                media.setGroupNameId(rs.getInt(1));
//                media.setGroupName(rs.getString(2));
                mediaGroups.add(media);
            }

//            mediaGroups.get(0).getMediaList().add(new Image());
//            mediaGroups.get(1).getMediaList().add(new Video());
//            mediaGroups.get(2).getMediaList().add(new Audio());
//            mediaGroups.get(3).getMediaList().add(new Video());
//            mediaGroups.get(4).getMediaList().add(new Video());
//            mediaGroups.get(5).getMediaList().add(new Video());

            mediaNameCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaInterface, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaInterface, String> param) {
                            return new SimpleStringProperty(
                                    ((MediaGroup)param.getValue()).getGroupName()
//                                    param.getValue().getGroupName()
                            );
                        }
                    }
            );

            mediaTypeCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaInterface, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaInterface, String> param) {
                            return new SimpleStringProperty(
//                                    param.getValue().getMediaList().get(0).getClass().getSimpleName()
                            );
                        }
                    }
            );

            mediaTypeCol.setCellFactory(
                    new Callback<TableColumn<MediaInterface, String>, TableCell<MediaInterface, String>>() {
                        @Override
                        public TableCell call(TableColumn<MediaInterface, String> param) {

                            TableCell<MediaInterface, Button> cell = new TableCell<>();

                            if (count > mediaGroups.size()) return cell;

                            Button bt = new Button("New");
                            cell.setGraphic(bt);

                            bt.setOnAction(
                                    new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            System.out.println("New button clicked");
                                            System.out.println("bounds: " + cell.getBoundsInParent());
                                            System.out.println(bt.getBoundsInParent());
                                            bt.setMaxWidth(cell.getWidth());
                                            bt.setMaxHeight(cell.getHeight());
                                            int b = ((MediaGroup)param.getTableView().getItems().get(
                                                    cell.getIndex()
                                                    )).getGroupNameId();
//                                            ).getGroupNameId();
                                            bt.setText(String.valueOf(b));
                                        }
                                    }
                            );
                            count++;
                            return cell;
                        }
                    }
            );
            tableViewMedia.setItems(mediaGroups);
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

    public TableView<MediaInterface> getTableViewMedia() {
        return tableViewMedia;
    }
}
