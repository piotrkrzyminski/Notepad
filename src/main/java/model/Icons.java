package model;

import javafx.scene.image.Image;

/**
 * Loads image files and assign it to variables
 */
public class Icons {
    public static final Image UNSAVED = new Image(String.valueOf(Icons.class.getResource("/images/unsavedTabIcon.png")));
    public static final Image SAVED = new Image(String.valueOf(Icons.class.getResource("/images/savedTabIcon.png")));
}
