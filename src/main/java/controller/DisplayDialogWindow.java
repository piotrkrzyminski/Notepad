package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created on 27.10.2017 by Piotr Krzyminski
 */
public class DisplayDialogWindow {
    public static  void displayDialog(Stage parentStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(DisplayDialogWindow.class.getResource("/FXML/SaveFileWindow.fxml"));
            System.out.println("Window is displayed");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();

        stage.setTitle("Notepad");

        if (root != null)
            stage.setScene(new Scene(root));

        stage.setResizable(false);

        stage.initOwner(parentStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
