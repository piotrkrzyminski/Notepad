package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SaveFileWindowController {
    @FXML
    private Button yesButton;

    @FXML
    public void yesButton() {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void noButton() {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }
}
