package services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.TabModel;

import java.io.File;
import java.io.IOException;

/**
 * Created on 26.10.2017 by Piotr Krzyminski
 * Display save file dialog to select path and save fileModel data to file
 */
public class SaveAsService implements FileOperation {

    public void performOperation(Stage window, TabModel tabModel) {
        FileChooser fileChooser = new FileChooser();

        /*set filters for file chooser*/
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", " *.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(window);

        /*Perform save to file*/
        if(file!=null) {
            try {
                WriteService.write(tabModel, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
