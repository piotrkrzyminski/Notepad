package services;

import model.FileModel;
import model.Icons;
import model.TabModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * created on 26.10.2017 by Piotr Krzyminski
 *
 * get FileModel and File object. Assign path and selected file name to FileModel fields and save it in selected location
 */
class WriteService {
    static void write(TabModel tabModel, File file) throws IOException {

        FileModel fileModel = tabModel.getFileModel();

        fileModel.setContent(tabModel.getTextArea().getText()); //Save text area content to fileModel field
        fileModel.setFileName(file.getName());
        fileModel.setFilePath(file.getAbsolutePath());

        /*Write to file using BufferedWriter*/
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileModel.getFilePath(), false));
        bufferedWriter.write(fileModel.getContent());
        bufferedWriter.close();

        fileModel.setSaved(true); //set File Model save flag to true
        tabModel.changeTabIcon(Icons.SAVED);
    }
}
