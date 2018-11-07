package com.swozniak;

/*@author: Stanisław Woźniak
@title: Main   */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{ //create main window
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/welcome.fxml"));
        primaryStage.setTitle("Primes Numbers");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.setMaxHeight(400);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxWidth(700);
        primaryStage.setMinWidth(700);
        primaryStage.show();
    }


    public static void main(String[] args) { //main xD
        launch(args);
    }
}
