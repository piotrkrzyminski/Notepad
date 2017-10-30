package model;

import com.sun.javafx.scene.control.behavior.TabPaneBehavior;
import com.sun.javafx.scene.control.skin.TabPaneSkin;
import controller.DisplayDialogWindow;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.FileOperation;
import services.SaveAsService;
import services.SaveService;

/**
 * Created on 25.10.2017 by Piotr Krzyminski
 *
 * Represents one Tab with File Model associated to it
 */
public class TabModel extends Tab {
    private static TabPane tabPane = NotepadModel.getTabPane();
    private TextArea textArea;

    private String tabName;

    private boolean saving;

    private FileModel fileModel;

    /**
     * Create new File Model, add new Text Area to Tab, change icon of Tab to SAVED, add name to Tab, and add event on close
     */
    public TabModel() {
        fileModel = new FileModel();
        this.tabName = fileModel.getFileName();
        textArea = createTextArea();
        this.setContent(textArea);
        this.setText(fileModel.getFileName());
        changeTabIcon(Icons.SAVED);

        this.setOnCloseRequest(event -> {
            if(tabPane.getTabs().isEmpty()) {
                if(!fileModel.isSaved() && textArea.getText().isEmpty())
                    DisplayDialogWindow.displayDialog(NotepadModel.getWindow());

               /*Display Save File Dialog Window and wait for decision*/
                if(!fileModel.isSaved() && !textArea.getText().isEmpty())
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

                    NotepadModel.saveFile(this,fileOperation); //perform file saving
                    saving = false; //set user decision about saving back to false
                }

                FileModel.resetIndex();
                tabPane.getTabs().add(new TabModel());
            }
        });

        tabPane.getTabs().add(this);
    }

    public void setName(String name) {
        this.setText(name);
    }

    /**
     * Create new Text Area and add ChangeListener to it
     * @return new Text Area
     */
    private TextArea createTextArea() {
        TextArea textArea = new TextArea();

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if(fileModel.isSaved()) {
                fileModel.setSaved(false);
                changeTabIcon(Icons.UNSAVED);
            }
        });

        return textArea;
    }

    /**
     * Change Tab Model icon
     * @param image image of icon
     */
    public void changeTabIcon(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);
        this.setGraphic(imageView);
    }

    public static void setTabPane(TabPane t) {
        tabPane = t;
    }

    public FileModel getFileModel() {
        return fileModel;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setReadOnly() {
        if(textArea.isEditable())
            textArea.setEditable(false);
        else
            textArea.setEditable(true);
    }

    public void trim() {
        String text = textArea.getText().trim();
        textArea.setText(text);
    }

    public void wrapText() {
        if(textArea.isWrapText())
            textArea.setWrapText(false);
        else
            textArea.setWrapText(true);
    }

    @Override
    public String toString() {
        return "File name: "+fileModel.getFileName()+"\n"+
                "File path: "+fileModel.getFilePath()+"\n" +
                "File content: "+fileModel.getContent()+"\n"+
                "Is file saved: "+fileModel.isSaved()+"\n\n";
    }
}
