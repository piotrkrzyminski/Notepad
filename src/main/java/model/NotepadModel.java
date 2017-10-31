package model;

import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import services.FileOperation;
import services.OpenService;

import java.io.IOException;

/**
 * Created on 25.10.2017 by Piotr Krzyminski
 * Singleton
 * Creates new Tabs, remove and pass Tab Models attached to selected Tab
 */
public class NotepadModel  {
    private static TabPane tabPane;

    public static boolean saving; // if file needs to be saved

    private static int fontSize = 14;

    /**
     * Perform saving operation of fileOperation type on Tab Model in parameter
     * @param tabModel Selected Tab
     * @param fileOperation Type of saving operation
     */
    public static void saveFile(TabModel tabModel, FileOperation fileOperation) {
        FileModel fileModel = tabModel.getFileModel();

        /*Write to file*/
        try {
            fileOperation.performOperation((Stage)tabPane.getScene().getWindow(),tabModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tabModel.setText(fileModel.getFileName()); // set Tab name to file name
    }

    /**
     * Create new Tab and perform read from file. Send data to Tab Model
     */
    public static void openFile() {
        FileOperation openService = new OpenService();
        Stage window = (Stage)tabPane.getScene().getWindow();

        /*Get selected Tab*/
        TabModel tabModel = (TabModel) tabPane.getSelectionModel().getSelectedItem();

        /*if text area in selected tab is not empty. Create new tab and load data to it*/
        if(!tabModel.getTextArea().getText().isEmpty())
            tabModel = new TabModel();

        try {
            openService.performOperation(window,tabModel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileModel fileModel;
        fileModel = tabModel.getFileModel();

        tabModel.setText(fileModel.getFileName()); //set head text of Tab
        tabModel.getTextArea().setText(fileModel.getContent()); //set content of Text Area
    }


    public static void setTabPane(TabPane tp) {
        tabPane = tp;
    }

    static TabPane getTabPane() {
        return tabPane;
    }

    /**
     * Change Node font style by 2. The biggest value is 60
     * @param node Element that font must be changed
     */
    public static void zoomIn(Node node) {
        if(fontSize<=58) {
            fontSize+=2;
            node.setStyle("-fx-font-size: "+fontSize);
        }
    }

    /**
     * Change Node font style by 2. The smallest value is 6
     * @param node Element that font must be changed
     */
    public static void zoomOut(Node node) {
        if(fontSize>=8) {
            fontSize-=2;
            node.setStyle("-fx-font-size: "+fontSize);
        }
    }

    /**
     * Change Node font style by 2. The smallest value is 6
     * @param node Element that font must be changed
     */
    public static void restoreZoom(Node node) {
        fontSize = 14;
        node.setStyle("-fx-font-size:"+fontSize);
    }

    /**
     * Enable or disable full screen
     */
    public static void switchFullScreenMode() {
        Stage window = (Stage)tabPane.getScene().getWindow();
        if(window.isFullScreen())
            window.setFullScreen(false);
        else
            window.setFullScreen(true);
    }
}
