package ru.bakhuss.ScreenShotNew.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import ru.bakhuss.ScreenShotNew.MainClass;
import ru.bakhuss.ScreenShotNew.action.screen.*;
import ru.bakhuss.ScreenShotNew.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.dataBase.DataBaseFile;
import ru.bakhuss.ScreenShotNew.dataBase.SQLHandler;
import ru.bakhuss.ScreenShotNew.dataBase.SQLitePerson;
import ru.bakhuss.ScreenShotNew.model.person.Person;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class mainViewController {

    private double currentMainHeight;
    private Date date;

    @FXML
    private AnchorPane anchorMainViewTable;
    @FXML
    private AnchorPane anchorSetTable;
    @FXML
    private ToggleButton btTogOpenTable;
    @FXML
    private ToggleButton btTogScreen;
    @FXML
    private TextField tfScreenTime;
    @FXML
    private VBox vboxMainViewTable;
    @FXML
    private Label lbWidthSize;
    @FXML
    private Label lbHeightSize;
    @FXML
    private Label mainFrames;
    @FXML
    private ProgressIndicator progIndDoneScreen;

    private TableView<Person> viewPersons;
    private TableColumn<Person, String> surNameCol, firstNameCol, patronymicCol;
    private static ObservableList<Person> data = FXCollections.observableArrayList();
    private HBox hBox;
    private Label lbAdd, lbDel, lbSave, lbCountInDB;
    private static int countPersonsInDB = 0;

    private MainClass mainClass;

    public mainViewController() {
    }


    public void initialize() {
        lbWidthSize.setText(String.valueOf(MainClass.getScreenMaxWidth()));
        lbHeightSize.setText(String.valueOf(MainClass.getScreenMaxHeight()));
//        createTableColumns();

//        data = FXCollections.observableArrayList(
//                new Person("surname", "name", "patronymic"),
//                new Person("Surname", "name", "patronymic"),
//                new Person("surname", "Name", "patronymic"),
//                new Person("surname", "name", "Patronymic")
//
//        );

    }

    public void getDataFromDB() {
        SQLitePerson sqLitePerson = new SQLitePerson();
        data.addAll(FXCollections.observableArrayList(sqLitePerson.getAll()));
    }

    private void createTableColumns() {
        viewPersons = new TableView<>();
        surNameCol = new TableColumn<>("Surname");
        firstNameCol = new TableColumn<>("Name");
        patronymicCol = new TableColumn<>("Patronymic");
        patronymicCol.setEditable(true);
        viewPersons.setEditable(true);
        viewPersons.getColumns().addAll(surNameCol, firstNameCol, patronymicCol);
        viewPersons.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        surNameCol.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        patronymicCol.setCellValueFactory(cellData -> cellData.getValue().patronymicProperty());
        viewPersons.setItems(data);

        setOnEditCommitMyColumn();

        AnchorPane.setBottomAnchor(viewPersons, 25.0);
        AnchorPane.setLeftAnchor(viewPersons, 0.0);
        AnchorPane.setRightAnchor(viewPersons, 0.0);
        AnchorPane.setTopAnchor(viewPersons, 0.0);

        hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);

        lbAdd = new Label("+");
        lbAdd.setTooltip(new Tooltip("add"));
        lbDel = new Label("-");
        lbDel.setTooltip(new Tooltip("del"));
        lbSave = new Label("save");
        createLabel(lbAdd, 20.0);
        createLabel(lbDel, 20.0);
        createLabel(lbSave, 15.0);
        lbSave.setOnMouseReleased(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        for (Person p : getData()) {
                            if (p.getPersonIdInDB() != 0) continue;
                            SQLitePerson sqLitePerson = new SQLitePerson();
                            System.out.println(p.getSurname());
                            sqLitePerson.set(p);
                            updateCountPersonsFromDBInMain();
                            System.out.println("goodSetPerson");
                            System.out.println("id: " + p.getPersonIdInDB());
                        }
                    }
                }
        );
        lbAdd.setOnMouseReleased(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        getData().add(new Person("new", "new","new"));
                    }
                }
        );
        lbDel.setOnMouseReleased(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Person person = viewPersons.getFocusModel().getFocusedItem();
                        System.out.println( person.getSurname() );

                        if (person.getPersonIdInDB() != 0) {
                            SQLitePerson sqLitePerson = new SQLitePerson();
                            sqLitePerson.remove(person);
                            updateCountPersonsFromDBInMain();
                        }
                        getData().remove(person);

                    }
                }
        );

        lbCountInDB = new Label();
        setCountPersonsInDB(getData().size());
        updateCountPersonsFromDBInMain();

        System.out.println("count: " + getCountPersonsInDB());
        lbCountInDB.setPadding(new Insets(2.0, 0.0, 0.0, 50.0));

        hBox.getChildren().addAll(lbAdd, lbDel, lbSave, lbCountInDB);
        AnchorPane.setBottomAnchor(hBox,0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
    }

    public void createLabel(Label label, Double size) {
        label.setFont(new Font(size));
        label.setTextFill(Color.RED);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(2.0,0.0,0.0,10.0));
        label.setOnMouseEntered(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        label.setFont(new Font("System BOLD",size));
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

    public void setOnEditCommitMyColumn() {
        surNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> event) {
                        (event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setSurname(event.getNewValue());
                    }
                }
        );

        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> event) {
                        (event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setFirstName(event.getNewValue());
                    }
                }
        );

        patronymicCol.setCellFactory(TextFieldTableCell.forTableColumn());
        patronymicCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> event) {
                        (event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setPatronymic(event.getNewValue());
                    }
                }
        );
    }

    public MainClass getMainClass() {
        return mainClass;
    }

    public void setMainClass(MainClass mainClass) {
        this.mainClass = mainClass;
    }

    public void openTable(ActionEvent actionEvent) {
        if (btTogOpenTable.isSelected()) {
            MainClass.getPrimaryStage().setResizable(true);
            getDataFromDB();
            createTableColumns();
            anchorSetTable.getChildren().addAll(viewPersons, hBox);
            MainClass.getPrimaryStage().setMinHeight(MainClass.getMainMinHeight() + 200);

        } else {
            anchorSetTable.getChildren().removeAll(viewPersons, hBox);
            viewPersons = null;
            hBox = null;
            getData().clear();

            MainClass.getPrimaryStage().setResizable(false);
            MainClass.setMainMinSize();
            MainClass.getPrimaryStage().setMinHeight(MainClass.getMainMinHeight());
        }
    }

    public double getCurrentMainHeight() {
        return currentMainHeight;
    }

    public void setCurrentMainHeight(double currentMainHeight) {
        this.currentMainHeight = currentMainHeight;
    }

    public void screening(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            public void run() {
                int tempScreenTime = Integer.valueOf(tfScreenTime.getText());
                if (btTogScreen.isSelected() && tempScreenTime != 0) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            mainFrames.setText("0");
                            progIndDoneScreen.setProgress(0);
                        }
                    });
                    ScreenCapture.setTimeScreen(tempScreenTime);
                    btTogScreen.setDisable(true);
                    ExecutorService es = Executors.newSingleThreadExecutor();
                    es.execute(new Runnable() {
                        public void run() {
                            ScreenCapture.getScreen(new Person());
                        }
                    });
                    es.shutdown();

                    while (!es.isTerminated()) {
                    }

                    btTogScreen.setDisable(false);
                    btTogScreen.setSelected(false);
                    Platform.runLater(new Runnable() {
                        public void run() {
                            progIndDoneScreen.setProgress(100);
                            mainFrames.setText(String.valueOf(ScreenCapture.getFrames()));
                        }
                    });
                } else {
                    btTogScreen.setSelected(false);
                }
            }
        }).start();
    }

    public void getDBFile(ActionEvent actionEvent) {
        FileChooser getDBFile = new FileChooser();
        getDBFile.setTitle("Open DBFile");
        getDBFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQLite", "*.db"));

        File file = getDBFile.showOpenDialog(MainClass.getPrimaryStage());
        DataBaseFile.setDBFile(file);
    }

    public void createDBFile(ActionEvent actionEvent) {

        FileChooser createDBFile = new FileChooser();
        createDBFile.setTitle("Create DB file");
        createDBFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQLite", "*.db"));

        File file = createDBFile.showSaveDialog(MainClass.getPrimaryStage());

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataBaseFile.setDBFile(file);

        SQLHandler connect = new SQLHandler(DBType.sqlite);
        try {
            connect.connect();
            DataBaseFile.createDBStructure(connect.getStmt());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error: createDBFile");
        } finally {
            connect.disconnect();
        }

    }

    public static ObservableList<Person> getData() {
        System.out.println("dataSize: " + data.size());
        return data;
    }

    public static void setData(Person person) {
        mainViewController.data.add(person);
        System.out.println("dataSize: " + data.size());
    }

    public static int getCountPersonsInDB() {
        return countPersonsInDB;
    }

    public static void setCountPersonsInDB(int countPersonsInDB) {
        mainViewController.countPersonsInDB = countPersonsInDB;
    }

    public void updateCountPersonsFromDBInMain() {
        SQLitePerson sqLitePerson = new SQLitePerson();
        setCountPersonsInDB( sqLitePerson.getCountFromRep() );
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lbCountInDB.setText(String.valueOf(getCountPersonsInDB()));
            }
        });
    }
}
