package ru.bakhuss.ScreenShotNew.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import ru.bakhuss.ScreenShotNew.MainClass;
import ru.bakhuss.ScreenShotNew.action.screen.*;
import ru.bakhuss.ScreenShotNew.dataBase.DBType;
import ru.bakhuss.ScreenShotNew.dataBase.DataBaseFile;
import ru.bakhuss.ScreenShotNew.dataBase.SQLHandler;
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
    private TableColumn<Person, String> surNameColumn, name, patronymic, birthday;

    private MainClass mainClass;

    public mainViewController() {
    }


    public void initialize() {
        lbWidthSize.setText(String.valueOf(MainClass.getScreenMaxWidth()));
        lbHeightSize.setText(String.valueOf(MainClass.getScreenMaxHeight()));
//        createTableColumns();
    }

    private void createTableColumns() {
        viewPersons = new TableView<>();
        surNameColumn = new TableColumn<>("Surname");
        name = new TableColumn<>("Name");
        patronymic = new TableColumn<>("Patronymic");
        birthday = new TableColumn<>("Birthday");

        viewPersons.getColumns().addAll(surNameColumn, name, patronymic, birthday);
        viewPersons.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        AnchorPane.setBottomAnchor(viewPersons, 0.0);
        AnchorPane.setLeftAnchor(viewPersons, 0.0);
        AnchorPane.setRightAnchor(viewPersons, 0.0);
        AnchorPane.setTopAnchor(viewPersons, 0.0);
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
            createTableColumns();
            anchorSetTable.getChildren().add(viewPersons);
            MainClass.getPrimaryStage().setMinHeight(MainClass.getMainMinHeight() + 100);
        } else {
            anchorSetTable.getChildren().remove(viewPersons);
            viewPersons = null;

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

//                            Platform.runLater(new Runnable() {
//                                @Override
//                                public void run() {
//                                    viewPersons.getColumns().remove(birthday);
//                                    viewPersons.getColumns().add(0, birthday);
//                                }
//                            });

                            ScreenCapture.getScreen(new Person(new Date().toString()));
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
}
