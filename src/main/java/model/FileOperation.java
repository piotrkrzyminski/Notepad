package model;

import javafx.stage.Stage;

import java.io.IOException;

public interface FileOperation {
    void performOperation(Stage window, TabModel tabModel) throws IOException;
}
