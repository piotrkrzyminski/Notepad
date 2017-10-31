import controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainWindow.fxml"));

        Parent root = loader.load();
        MainWindowController controller = loader.getController(); //load controller

        /*Set new scene*/
        if(root != null) {
            stage.setScene(new Scene(root));
        }

        controller.setWindow(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
