package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.NotepadModel;

public class SaveFileWindowController {
    @FXML
    private Button yesButton;

    @FXML
    public void yesButton() {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        NotepadModel.getInstance().setSaving();
        stage.close();
    }

    @FXML
    public void noButton() {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }
}
