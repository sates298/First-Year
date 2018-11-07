package com.swozniak;

/*@author: Stanisław Woźniak
@title: Controller   */

import com.swozniak.PrimesNumbers;
import com.swozniak.WrongArgumentException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class WelcomeController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Text welcomeText;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private TextField range;
    @FXML
    private TextField howMany;
    @FXML
    private Text scoreText;
    @FXML
    private MenuBar menu;

    @FXML
    private void handleNextButtonAction() throws IOException {  //open dialog window and enter parameters
        String parameters;
        String mainParameter;
        String howManyParameters;
        scoreText.setText("");

        mainParameter = range.getText();
        howManyParameters = howMany.getText();

        if (range.getText().equals("") || howMany.getText().equals("")) {
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("Error");
            warning.setHeaderText("Error!");
            warning.setContentText("Something goes wrong...");
            warning.showAndWait();
        } else {

            TextInputDialog arguments = new TextInputDialog();
            arguments.setTitle("Arguments");
            arguments.setContentText("Please enter yours parameters: ");
            Optional<String> result = arguments.showAndWait();

            if (result.isPresent() && result.get().length() > 0) {
                parameters = result.get();

                try {
                    int n = Integer.parseInt(mainParameter);
                    int numberOfArguments = Integer.parseInt(howManyParameters);
                    if (n < 2 || numberOfArguments < 0) {
                        throw new WrongArgumentException();
                    }
                    PrimesNumbers primesNumber = new PrimesNumbers(n);
                    String[] splitParameters = parameters.split(" ");
                    if (splitParameters.length != numberOfArguments) {
                        throw new WrongArgumentException();
                    }
                    for (int i = 0; i < numberOfArguments; i++) {
                        try {
                            int arg = Integer.parseInt(splitParameters[i]);
                            scoreText.setText(scoreText.getText() + arg + ": " + Integer.toString(primesNumber.number(arg)) + "\n");
                        } catch (ArrayIndexOutOfBoundsException a) {
                            scoreText.setText(scoreText.getText() + splitParameters[i] + ": wrong number! \n");
                        } catch (NumberFormatException b) {
                            scoreText.setText(scoreText.getText() + splitParameters[i] + ": wrong format! \n");
                        }
                    }

                } catch (NumberFormatException | WrongArgumentException e) {
                    scoreText.setText("wrong data!");
                }
            } else {
                scoreText.setText("something goes wrong...");
            }

        }
        scoreText.setVisible(true);
    }

    @FXML
    private void handleExitAction() { //close
        Stage stage = (Stage) menu.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleChangeBackgroundColorAction() { //change background
        List<String> choices = new ArrayList<>();
        choices.add("starter color");
        choices.add("yellow");
        choices.add("cyan");

        ChoiceDialog<String> backgroundColor = new ChoiceDialog<>("starter color", choices);
        backgroundColor.setTitle("Background colors");
        backgroundColor.setHeaderText("Colors");
        backgroundColor.setContentText("Choose color: ");

        Optional<String> result = backgroundColor.showAndWait();
        if (result.isPresent()) {
            switch (result.get()) {
                case "starter color":
                    borderPane.
                            setStyle("-fx-background-color:" +
                                    " linear-gradient(to right top, yellowgreen, chartreuse, cornflowerblue)");
                    break;
                case "yellow":
                    borderPane.setStyle("-fx-background-color: yellow");
                    break;
                case "cyan":
                    borderPane.setStyle("-fx-background-color: cyan");
                    break;
            }
        }
    }

    @FXML
    private void handleChangeFontColorAction() { //change font color
        List<String> choices = new ArrayList<>();
        choices.add("black");
        choices.add("blue");
        choices.add("red");

        ChoiceDialog<String> fontColors = new ChoiceDialog<>("black", choices);
        fontColors.setTitle("Font colors");
        fontColors.setHeaderText("Colors");
        fontColors.setContentText("Choose color: ");

        Optional<String> result = fontColors.showAndWait();
        if (result.isPresent()) {
            switch (result.get()) {
                case "black":
                    welcomeText.setStyle("-fx-fill: black");
                    label1.setStyle("-fx-text-fill: black");
                    label2.setStyle("-fx-text-fill: black");
                    scoreText.setStyle("-fx-fill: black");
                    break;
                case "blue":
                    welcomeText.setStyle("-fx-fill: blue");
                    label1.setStyle("-fx-text-fill: blue");
                    label2.setStyle("-fx-text-fill: blue");
                    scoreText.setStyle("-fx-fill: blue");
                    break;
                case "red":
                    welcomeText.setStyle("-fx-fill: red");
                    label1.setStyle("-fx-text-fill: red");
                    label2.setStyle("-fx-text-fill: red");
                    scoreText.setStyle("-fx-fill: red");
                    break;
            }
        }
    }

}
