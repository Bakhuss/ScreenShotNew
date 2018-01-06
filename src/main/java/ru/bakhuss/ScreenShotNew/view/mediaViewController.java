package ru.bakhuss.ScreenShotNew.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ObservableList<MediaAbstract> mediaGroups = FXCollections.observableArrayList();
    private int count = -1;
    private MainClass mainClass;

    @FXML
    private AnchorPane mediaViewParent;
    @FXML
    private Label lbAdd, lbDel, lbSave;
    @FXML
    private TableView<MediaAbstract> tableViewMedia;
    @FXML
    private TableColumn<MediaAbstract, String> mediaNameCol, mediaGroupCol, mediaTypeCol;


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
                img.setNameId(Long.valueOf(rs.getInt(1)));
                img.setId(Long.valueOf(rs.getInt(2)));
                img.setName(rs.getString(3));
                mediaGroups.add(img);
            }
            System.out.println("mediaGroups: " + mediaGroups.size());

            mediaNameCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaAbstract, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaAbstract, String> param) {
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
                MediaGroup<Image> media = new MediaGroup<>();
                if (rs.getInt(4) == 1) autoscreen = true;
                media.setAutoscreen(autoscreen);
//                MediaGroup<Image> media = new MediaGroup<>(autoscreen);
                media.setId(Long.valueOf(rs.getInt(1)));
                media.setName(rs.getString(2));
                media.setElementsCount(rs.getInt(5));
                mediaGroups.add(media);
            }

            tableViewMedia.setItems(mediaGroups);
            tableViewMedia.setEditable(true);

            mediaNameCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaAbstract, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaAbstract, String> param) {
                            return new SimpleStringProperty(
                                    ((MediaGroup)param.getValue()).getName()
                            );
                        }
                    }
            );

            mediaGroupCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaAbstract, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaAbstract, String> param) {
                            int count = ((MediaGroup)param.getValue()).getElementsCount();
                            return new SimpleStringProperty(String.valueOf(count));
                        }
                    }
            );

            mediaGroupCol.setOnEditStart(
                    new EventHandler<TableColumn.CellEditEvent<MediaAbstract, String>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<MediaAbstract, String> event) {
                            System.out.println("count: " + ((MediaGroup)event.getRowValue()).getElementsCount() );
                        }
                    }
            );

            mediaTypeCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<MediaAbstract, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<MediaAbstract, String> param) {
                            String str = param.getValue().getClass().getSimpleName();
                            return new SimpleStringProperty(str);
                        }
                    }
            );


///*
//            mediaTypeCol.setCellFactory(
//                    new Callback<TableColumn<MediaAbstract, String>, TableCell<MediaAbstract, String>>() {
//                        @Override
//                        public TableCell call(TableColumn<MediaAbstract, String> param) {
//                            TableCell<MediaAbstract, Button> cell = new TableCell<>();
//                            if (count >= mediaGroups.size()) return cell;
//                            Button bt = new Button("New");
//                            if (count == mediaGroups.size()) bt.setText("new");
//                            cell.setGraphic(bt);
//                            cell.setAlignment(Pos.CENTER);
//                            bt.setOnAction(
//                                    new EventHandler<ActionEvent>() {
//                                        @Override
//                                        public void handle(ActionEvent event) {
////                                            System.out.println("bounds: " + cell.getBoundsInParent());
////                                            System.out.println(bt.getBoundsInParent());
//                                            bt.setMaxWidth(cell.getWidth()-30);
//                                            bt.setMaxHeight(cell.getHeight());
//                                            System.out.println("cellIndex: " + cell.getIndex());
//                                            int b = ((MediaGroup)param.getTableView().getItems().get(
//                                                    cell.getIndex()
//                                                    )).getGroupNameId();
//                                            bt.setText(String.valueOf(b));
//                                        }
//                                    }
//                            );
//                            count++;
//                            return cell;
//                        }
//                    }
//            );
//*/

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

    public TableView<MediaAbstract> getTableViewMedia() {
        return tableViewMedia;
    }

    public MainClass getMainClass() {
        return mainClass;
    }

    public void setMainClass(MainClass mainClass) {
        this.mainClass = mainClass;
    }
}
