package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveFileWindow implements DisplayWindow {
    @Override
    public void display(Stage window) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SaveFileWindow.fxml"));

        Parent root = loader.load();
        SaveFileWindowController controller = loader.getController(); //load controller

        Stage stage = new Stage();

        /*Set new scene*/
        if(root != null) {
            stage.setScene(new Scene(root));
        }

        controller.setWindow(stage);

        stage.setResizable(false);

        /*Set window modal*/
        stage.initOwner(window);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
