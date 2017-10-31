package model;

import controller.SaveFileWindow;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.FileOperation;
import services.SaveAsService;
import services.SaveService;

import java.io.IOException;

/**
 * Created on 25.10.2017 by Piotr Krzyminski
 *
 * Represents one Tab with File Model associated to it
 */
public class TabModel extends Tab {
    private static TabPane tabPane = NotepadModel.getTabPane();
    private TextArea textArea;

    private FileModel fileModel;

    /**
     * Create new File Model, add new Text Area to Tab, change icon of Tab to SAVED, add name to Tab, and add event on close
     */
    public TabModel() {
        fileModel = new FileModel();
        textArea = createTextArea();
        this.setContent(textArea);
        this.setText(fileModel.getFileName());
        changeTabIcon(Icons.SAVED);

        this.setOnCloseRequest(event -> {
            Stage window = (Stage)tabPane.getScene().getWindow();
            if(!fileModel.isSaved()) {
                SaveFileWindow saveFileWindow = new SaveFileWindow();

                try {
                    saveFileWindow.display(window);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(NotepadModel.saving) {
                FileOperation fileOperation;

                if(fileModel.getFilePath().equals(""))
                    fileOperation = new SaveAsService();
                else
                    fileOperation = new SaveService();

                NotepadModel.saveFile(this,fileOperation);
                NotepadModel.saving = false;
            }

            if(!tabPane.getTabs().isEmpty()) {
                tabPane.getTabs().remove(this);
            }
        });
    }

    public void close() {
        EventHandler<Event> handler = this.getOnCloseRequest();
        if (null != handler) {
            handler.handle(null);
        } else {
            tabPane.getTabs().remove(this);
        }

        if(tabPane.getTabs().isEmpty()){
            FileModel.resetIndex();
            tabPane.getTabs().add(new TabModel());
        }
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

    /**
     * Enable or disable read only text area option
     */
    public void setReadOnly() {
        if(textArea.isEditable())
            textArea.setEditable(false);
        else
            textArea.setEditable(true);
    }

    /**
     * delete white spaces in the beginning and end of text in text area
     */
    public void trim() {
        String text = textArea.getText().trim();
        textArea.setText(text);
    }

    /**
     * enable or disable wrap text option
     */
    public void wrapText() {
        if(textArea.isWrapText())
            textArea.setWrapText(false);
        else
            textArea.setWrapText(true);
    }
}
