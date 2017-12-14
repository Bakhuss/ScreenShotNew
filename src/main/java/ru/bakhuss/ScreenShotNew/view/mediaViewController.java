package ru.bakhuss.ScreenShotNew.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import ru.bakhuss.ScreenShotNew.model.person.Person;

public class mediaViewController {

    private Stage dialogStage;
    private Person person;

    @FXML
    private void initialize() {
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
}
