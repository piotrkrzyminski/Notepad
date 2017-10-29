package model;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created on 25.10.2017 by Piotr Krzyminski
 *
 * Represents one Tab with File Model associated to it
 */
public class TabModel {
    private Tab tab;
    private TextArea textArea;

    private FileModel fileModel;

    /**
     * Constructor create new FileModel object
     */
    TabModel() {
        fileModel = new FileModel();
    }
    /**
     * Create new Tab with TextArea and add it to TabPane
     *
     * @return created Tab
      */
    Tab createTab() {
        Tab tab;
        tab = new Tab(fileModel.getFileName());

        /*Add action on tab close*/
        tab.setOnCloseRequest(event -> NotepadModel.getInstance().removeTab(tab));

        TextArea textArea = createTextArea();
        tab.setContent(textArea); // add new Text Area to Tab

        this.textArea = textArea;
        this.tab = tab;

        changeTabIcon(Icons.SAVED);

        return tab;
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
     * Change Tab icon
     * Sets width and height to 16px
     * @param image saved or unsaved image of icon
     */
    public void changeTabIcon(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);
        tab.setGraphic(imageView);
    }

    public FileModel getFileModel() {
        return fileModel;
    }

    TextArea getTextArea() {
        return textArea;
    }

    @Override
    public String toString() {

        return "File name: "+fileModel.getFileName()+"\n"+
                "File path: "+fileModel.getFilePath()+"\n" +
                "File content: "+fileModel.getContent()+"\n"+
                "Is file saved: "+fileModel.isSaved()+"\n\n";
    }
}
