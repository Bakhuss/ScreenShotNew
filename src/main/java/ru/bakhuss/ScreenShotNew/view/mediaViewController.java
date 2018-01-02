package ru.bakhuss.ScreenShotNew.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.bakhuss.ScreenShotNew.MainClass;
import ru.bakhuss.ScreenShotNew.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.dataBase.SQLHandler;
import ru.bakhuss.ScreenShotNew.model.media.*;
import ru.bakhuss.ScreenShotNew.model.person.Person;
import ru.bakhuss.ScreenShotNew.view.myFXObjects.MyLabel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class mediaViewController {

    private Stage dialogStage;
    private Person person;
    private static HashSet<Person> persons;
    private String mediaType;
    private ObservableList<Media> medias = FXCollections.observableArrayList();
    private ObservableList<MediaInterface> mediaGroups = FXCollections.observableArrayList();
    private int count = -1;
    private MainClass mainClass;

    @FXML
    private AnchorPane mediaViewParent;
    @FXML
    private Label lbAdd, lbDel, lbSave;
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
                Image img = new Image();
                img.setId(rs.getInt(1));
                img.setName(rs.getString(2));
                mediaGroups.add(img);
            }
            System.out.println("mediaGroups: " + mediaGroups.size());

            mediaNameCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaInterface, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaInterface, String> param) {
                            return new SimpleStringProperty(
                                    ((Image)param.getValue()).getName()
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
                ((MediaGroup)media).setElementsCount(rs.getInt(4));
                mediaGroups.add(media);
            }

            tableViewMedia.setItems(mediaGroups);

            mediaNameCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaInterface, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaInterface, String> param) {
                            return new SimpleStringProperty(
                                    ((MediaGroup)param.getValue()).getGroupName()
                            );
                        }
                    }
            );

            mediaGroupCol.setCellFactory(
                    new Callback<TableColumn<MediaInterface, String>, TableCell<MediaInterface, String>>() {
                        @Override
                        public TableCell call(TableColumn<MediaInterface, String> param) {
                            TableCell<MediaInterface, String> cell = new TableCell<>();
//                            count++;
                            System.out.println("count: " + count);
                            System.out.println(mediaGroups.size());
                            Button bt = new Button("bt");
                            if (count > mediaGroups.size()) {
                                System.out.println(11111);
                                return cell;
                            } else {

                                if (count >= 0 && count < mediaGroups.size()) {
                                    String cls = mediaGroups.get(count).getClass().getSimpleName();

                                    if (!cls.equals("MediaGroup")) return cell;
                                    else {
                                        int elementsCount = ((MediaGroup) mediaGroups.get(count)).getElementsCount();
                                        System.out.println("elements: " + elementsCount);
                                        bt.setText(String.valueOf(elementsCount));
                                    }
                                }
                            }



                            cell.setGraphic(bt);

                            return cell;
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
                            System.out.println("count: " + count);
                            if (count > mediaGroups.size()) {
                                System.out.println(222222);
                                return cell;
                            }
                            Button bt = new Button("New");
                            if (count == 0) {
                                System.out.println(3333);
                                bt.setText("NEW");
                            }
                            if (count == mediaGroups.size()) bt.setText("new");
                            cell.setGraphic(bt);
                            bt.setOnAction(
                                    new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
//                                            System.out.println("bounds: " + cell.getBoundsInParent());
//                                            System.out.println(bt.getBoundsInParent());
                                            bt.setMaxWidth(cell.getWidth());
                                            bt.setMaxHeight(cell.getHeight());
                                            System.out.println("cellIndex: " + cell.getIndex());
                                            int b = ((MediaGroup)param.getTableView().getItems().get(
                                                    cell.getIndex()
                                                    )).getGroupNameId();
                                            bt.setText(String.valueOf(b));
                                        }
                                    }
                            );
                            count++;
                            return cell;
                        }
                    }
            );




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlite.disconnect();
        }
//        tableViewMedia.setItems(mediaGroups);
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

    public MainClass getMainClass() {
        return mainClass;
    }

    public void setMainClass(MainClass mainClass) {
        this.mainClass = mainClass;
    }
}
