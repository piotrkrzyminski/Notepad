package services;

import javafx.stage.Stage;
import model.TabModel;

import java.io.File;
import java.io.IOException;

/**
 * Created on 26.10.2017 by Piotr Krzyminski
 * Save file without displaying FileChooser window
 */
public class SaveService implements FileOperation {

    public void performOperation(Stage window, TabModel tabModel) throws IOException {

        File file = new File(tabModel.getFileModel().getFilePath());

        try {
            WriteService.write(tabModel, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}