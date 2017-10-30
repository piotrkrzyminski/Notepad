package services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.TabModel;

import java.io.File;
import java.io.IOException;

public class OpenService implements FileOperation {
    @Override
    public void performOperation(Stage window, TabModel tabModel) throws IOException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", " *.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(window);

        if(file != null) {
            try {
                ReadService.read(tabModel,file);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}