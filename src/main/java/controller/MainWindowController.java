package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.FileModel;
import model.Icons;
import model.NotepadModel;
import model.TabModel;
import services.FileOperation;
import services.SaveAsService;
import services.SaveService;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    @FXML
    private TabPane tabPane;
    @FXML
    private BorderPane borderPane;

    private FileOperation fileOperation;

    private Stage window;

    /**
     * Add one tab on startup
     * Get TexArea of first Tab
     * Every time textArea text is changed change File Model saved flag to false
     * Every time tab is changed load Text Area from active Tab
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        TabModel.setTabPane(tabPane);
        NotepadModel.setTabPane(tabPane);
        tabPane.getTabs().add(new TabModel());
    }

    @FXML
    public void createTab() {
        tabPane.getTabs().add(new TabModel());
    }

    /**
     * Removes selected Tab on close request
     * If Tab Pane is empty after remove add new card
     */
    @FXML
    public void removeTab() {
        TabModel tabModel = (TabModel) tabPane.getSelectionModel().getSelectedItem();
        tabModel.close();
    }

    @FXML
    public void save() {
        TabModel tabModel = (TabModel) tabPane.getSelectionModel().getSelectedItem();
        FileModel fileModel = tabModel.getFileModel();

        if(fileModel.getFilePath().equals(""))
            fileOperation = new SaveAsService();
        else
            fileOperation = new SaveService();

        NotepadModel.saveFile(tabModel, fileOperation);
    }

    /**
     * Perform open file operation. After file is opened change tab icon to SAVED and File Model saved flag to true
     */
    @FXML
    public void open() {
        TabModel tabModel = (TabModel) tabPane.getSelectionModel().getSelectedItem();
        NotepadModel.openFile();
        tabModel.changeTabIcon(Icons.SAVED);
        tabModel.getFileModel().setSaved(true);
    }

    @FXML
    public void zoomIn() {
        NotepadModel.zoomIn(borderPane);
    }

    @FXML
    public void zoomOut() {
        NotepadModel.zoomOut(borderPane);
    }

    @FXML
    public void restoreZoom() {
        NotepadModel.restoreZoom(borderPane);
    }

    @FXML void switchFullScreenMode() {
        NotepadModel.switchFullScreenMode();
    }

    @FXML
    public void setReadOnly() {
        TabModel tabModel = (TabModel) tabPane.getSelectionModel().getSelectedItem();
        tabModel.setReadOnly();
    }

    @FXML
    public void trimTextAreaContent() {
        TabModel tabModel = (TabModel) tabPane.getSelectionModel().getSelectedItem();
        tabModel.trim();
    }

    @FXML
    public void wrapText() {
        TabModel tabModel = (TabModel) tabPane.getSelectionModel().getSelectedItem();
        tabModel.wrapText();
    }

    public void setWindow(Stage window) {
        this.window = window;
    }
}