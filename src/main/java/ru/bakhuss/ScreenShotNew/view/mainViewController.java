package ru.bakhuss.ScreenShotNew.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.bakhuss.ScreenShotNew.MainClass;
import ru.bakhuss.ScreenShotNew.action.screen.*;
import ru.bakhuss.ScreenShotNew.model.person.Person;

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

    private MainClass mainClass;

    TableColumn<Person, String> birthday = null;

    public mainViewController() {
    }


    public void initialize() {
        lbWidthSize.setText(String.valueOf(MainClass.getScreenMaxWidth()));
        lbHeightSize.setText(String.valueOf(MainClass.getScreenMaxHeight()));
//        createTableColumns();
    }

    private void createTableColumns() {
        viewPersons = new TableView<Person>();
        TableColumn<Person, String> surNameColumn = new TableColumn<Person, String>("Surname");
        TableColumn<Person, String> name = new TableColumn<Person, String>("Name");
        TableColumn<Person, String> patronymic = new TableColumn<Person, String>("Patronymic");
        birthday = new TableColumn<Person, String>("Birthday");
//        TableColumn<Person, String> birthday = new TableColumn<Person, String>("Birthday");

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
                            if (viewPersons.isTableMenuButtonVisible()) {
                                viewPersons.setTableMenuButtonVisible(false);
                            } else viewPersons.setTableMenuButtonVisible(true);

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    viewPersons.getColumns().remove(birthday);
                                    viewPersons.getColumns().add(0, birthday);
                                }
                            });

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
}
