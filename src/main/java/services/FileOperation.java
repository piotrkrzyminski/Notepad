package services;

import javafx.stage.Stage;
import model.TabModel;

import java.io.IOException;

public interface FileOperation {
    void performOperation(Stage window, TabModel tabModel) throws IOException;
}
