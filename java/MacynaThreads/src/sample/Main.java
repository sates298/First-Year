package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Stanislaw Wozniak
 */
public class Main extends Application {


    /**
     * method where is create a window
     *
     * @param primaryStage stage for foundation of fxml file
     * @throws Exception and it is not catched
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Macyna Trey");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }


    /**
     * main method
     *
     * @param args arguments entered with run programm
     */
    public static void main(String[] args) {
        launch(args);

    }
}
