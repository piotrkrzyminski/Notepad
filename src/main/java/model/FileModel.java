package model;

/**
 * Created on 25.10.2017 by Piotr Krzyminski
 *
 * Model of file
 * Store information about file name, content, path and if it is saved or not
 */
public class FileModel {
    private String fileName;
    private String content;
    private String filePath;
    private static int index = 1;

    private boolean isSaved;

    /**
     * Non parameter constructor. Assign default values
     */
    FileModel() {
        fileName = "new "+index++;
        content = "";
        filePath = "";
        isSaved = true;
    }

    /*--------------GETTERS--------------------*/

    String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    String getContent() {
        return content;
    }

    public boolean isSaved() {
        return isSaved;
    }

    /*-----------SETTERS-----------------*/

    void setFileName(String fileName) {
        this.fileName = fileName;
    }

    void setContent(String content) {
        this.content = content;
    }

    void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    public static void resetIndex() {
        index = 1;
    }
}