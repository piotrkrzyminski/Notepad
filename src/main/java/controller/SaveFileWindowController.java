package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.NotepadModel;

public class SaveFileWindowController {
    private Stage window;

    @FXML
    public void yesButton() {
        NotepadModel.saving = true;
        window.close();
    }

    @FXML
    public void noButton() {
        window.close();
    }

    public void setWindow(Stage window) {
        this.window = window;
    }
}
