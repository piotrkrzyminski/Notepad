package services;

import model.FileModel;
import model.TabModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created on 29.10.2017 by Piotr Krzyminski
 * Read text from File passed by parameter and assign fields in FileModel
 */
public class ReadService {
    /**
     * Read text and pass values to File Model
     * @param tabModel TabModel conained File Model where fields will by assignes
     * @param file Representation of readed file
     * @throws IOException Throw exception when reading ends with error
     */
    public static void read(TabModel tabModel, File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        FileModel fileModel = tabModel.getFileModel();

        StringBuilder stringBuilder = new StringBuilder();

        String line;

        while((line = bufferedReader.readLine())!=null) {
            stringBuilder.append(line);
        }

        bufferedReader.close();
        System.out.println(stringBuilder.toString());

        /*Set field in FileModel*/
        fileModel.setFilePath(file.getAbsolutePath());
        fileModel.setFileName(file.getName());
        fileModel.setContent(stringBuilder.toString());
    }
}
