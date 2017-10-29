package model;

import controller.DisplayDialogWindow;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 * Created on 25.10.2017 by Piotr Krzyminski
 * Singleton
 * Creates new Tabs, remove and pass Tab Models attached to selected Tab
 */
public class NotepadModel  {
    private static NotepadModel notepadModel = null;

    private TabPane tabPane; // Tab Pane which stores Tabs

    private static Map<Tab,TabModel> tabModelMap; //stores Tabs and associated TabModels with it

    private boolean saving = false; //value get from SaveDialog: True - save data from tab and close it. False - close tab without saving


    private int fontSize = 14;

    private NotepadModel() {
        tabModelMap = new HashMap<>();
    }

    /**
     * Create one instance of NotepadModel
     * @return new NotepadModel instance
     */
    public static NotepadModel getInstance() {
        if(notepadModel == null) {
            notepadModel = new NotepadModel();
        }

        return notepadModel;
    }

    /**
     * Add new Tab to TabPane
     */
    public void createTab() {
        TabModel tabModel = new TabModel();
        Tab tab = tabModel.createTab();

        tabModelMap.put(tab,tabModel);
        tabPane.getTabs().add(tab);
    }

    /**
     * Remove tab from TabPane and Map
     * @param tab Tab to close
     */
    public void removeTab(Tab tab) {
        TabModel tabModel = getTabModel(tab);
        FileModel fileModel = tabModel.getFileModel(); //File Model associated with selected Tab;

        /*Display Save File Dialog Window and wait for decision*/
        if(!tabModel.getFileModel().isSaved() && !tabModel.getTextArea().getText().isEmpty())
            DisplayDialogWindow.displayDialog((Stage)tabPane.getScene().getWindow());

        /*
         * if user selected YES button perform file saving and set flag to false
         * if file was saved before perform quick save else normal save operation
         * File was saved ealier if File Model path variable not equals ""
         */
        if(saving) {
            FileOperation fileOperation;

            if(fileModel.getFilePath().equals(""))
                fileOperation = new SaveAsService(); //Normal saving
            else
                fileOperation = new SaveService(); //Quick saving

            saveFile(fileOperation); //perform file saving
            saving = false; //set user decision about saving back to false
        }

        /*When user wants to close Tab find proper TabModel in list and delete it*/
        if(tabModelMap.containsKey(tab))
            tabModelMap.remove(tab);

        tabPane.getTabs().remove(tab); //delete tab from Tab Pane

        /*If closing last Tab reset FileModel index to 1 and create new Tab*/
        if(tabPane.getTabs().isEmpty()) {
            FileModel.resetIndex();
            createTab();
        }
    }

    /**
     * Save file to selected location
     * @param fileOperation type of saving
     */
    public void saveFile(FileOperation fileOperation) {
        Stage window = (Stage)tabPane.getScene().getWindow();
        Tab tab = tabPane.getSelectionModel().getSelectedItem();

        TabModel tabModel = getTabModel(tab);
        FileModel fileModel = tabModel.getFileModel();

        /*Write to file*/
        try {
            fileOperation.performOperation(window,tabModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tab.setText(fileModel.getFileName()); // set Tab name to file name
    }

    /**
     * Create new Tab and perform read from file. Send data to Tab Model
     */
    public void openFile() {
        FileOperation openService = new OpenService();
        Stage window = (Stage)tabPane.getScene().getWindow();

        /*Create new tab*/
        createTab();
        Tab tab = tabPane.getSelectionModel().getSelectedItem();

        TabModel tabModel = getTabModel(tab);

        try {
            openService.performOperation(window,tabModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileModel fileModel = tabModel.getFileModel();

        tab.setText(fileModel.getFileName()); //set head text of Tab
        tabModel.getTextArea().setText(fileModel.getContent()); //set content of Text Area
    }

    /**
     * return Tab Model from map based on tab in parameter
     * tab is a key
     * @param tab Get TabModel form Tab in parameter
     * @return TabModel assigned to Tab passed in parameter
     */
    public TabModel getTabModel(Tab tab) {
        return tabModelMap.get(tab);
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void setSaving() {
        saving = true;
    }

    /**
     * Change Node font style by 2. The biggest value is 60
     * @param node Element that font must be changed
     */
    public void increseFontSize(Node node) {
        if(fontSize<=58) {
            fontSize+=2;
            node.setStyle("-fx-font-size: "+fontSize);
        }
    }

    /**
     * Change Node font style by 2. The smallest value is 6
     * @param node Element that font must be changed
     */
    public void decreseFontSize(Node node) {
        if(fontSize>=8) {
            fontSize-=2;
            node.setStyle("-fx-font-size: "+fontSize);
        }
    }
}
