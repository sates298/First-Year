package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;

import javafx.scene.layout.*;

/**
 * Controller of fxml file
 */
public class Controller {

    /**
     * numbers of rows
     */
    @FXML
    private TextField m;
    /**
     * numbers of columns
     */
    @FXML
    private TextField n;
    /**
     * value of speed and delay
     */
    @FXML
    private TextField k;
    /**
     * probability to change colors
     */
    @FXML
    private TextField p;
    /**
     * GridPane where are all fields
     * @see SetUp
     */
    @FXML
    private GridPane pane;


    /**
     * method where is main function and run all threads
     * @see Quadrangle
     * @see SetUp
     */
    @FXML
    private void startApplication() {

        try {
            int nOne = Integer.parseInt(n.getText());
            int mOne = Integer.parseInt(m.getText());
            Long speed = Long.parseLong(k.getText());
            double probability = Double.parseDouble(p.getText());
            if (nOne < 1 || mOne < 1 || speed <= 0 || probability > 1 || probability < 0) {
                throw new ThreadException();
            }

            SetUp setUp = new SetUp(pane, nOne, mOne, probability, speed);
            pane = setUp.getGrid();
            Quadrangle[][] ar = setUp.getArray();

            for (int i = 0; i < mOne; i++) {
                for (int j = 0; j < nOne; j++) {
                    (new Thread(ar[i][j])).start();
                }
            }

        } catch (NumberFormatException | ThreadException e) {
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("error!");
            warning.setHeaderText("ERROR!!!");
            warning.setContentText("Something went wrong!");
            warning.showAndWait();
        }
    }
}
