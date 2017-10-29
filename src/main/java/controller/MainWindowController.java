package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.*;

public class MainWindowController {
    @FXML
    private TabPane tabPane;
    @FXML
    private BorderPane borderPane;

    private FileOperation fileOperation;


    private NotepadModel notepadModel = NotepadModel.getInstance();

    /**
     * Add one tab on startup
     * Get TexArea of first Tab
     * Every time textArea text is changed change File Model saved flag to false
     * Every time tab is changed load Text Area from active Tab
     */
    @FXML
    public void initialize() {
        notepadModel.setTabPane(tabPane);

        notepadModel.createTab();

    }

    /**
     * Add new tab to TabPane
     */
    @FXML
    public void createTab() {
        notepadModel.createTab();
    }

    /**
     * if tabPane has some cards remove from TabPane active tab
     */
    @FXML
    public void removeTab() {
        Tab tab = getSelectedTab();
        notepadModel.removeTab(tab);
    }

    /**
     * Get TabModel by active Tab and save tab text area content to file
     */
    @FXML
    public void save() {
        TabModel tabModel = NotepadModel.getInstance().getTabModel(getSelectedTab());
        FileModel fileModel = tabModel.getFileModel();

        if(fileModel.getFilePath().equals(""))
            fileOperation = new SaveAsService();
        else
            fileOperation = new SaveService();

        notepadModel.saveFile(fileOperation);
    }

    /**
     * Perform open file operation. After file is opened change tab icon to SAVED and File Model saved flag to true
     */
    @FXML
    public void open() {
        Tab tab = getSelectedTab();
        NotepadModel.getInstance().openFile();
        NotepadModel.getInstance().getTabModel(getSelectedTab()).changeTabIcon(Icons.SAVED);
        TabModel tabModel = NotepadModel.getInstance().getTabModel(tab);
        tabModel.getFileModel().setSaved(true);
    }

    @FXML
    public void increseFontSize() {
        NotepadModel.getInstance().increseFontSize(borderPane);
    }

    @FXML
    public void decreseFontSize() {
        NotepadModel.getInstance().decreseFontSize(borderPane);
    }


    private Tab getSelectedTab() {
        return tabPane.getSelectionModel().getSelectedItem();
    }
}