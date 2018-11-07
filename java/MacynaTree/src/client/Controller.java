package client;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import server.TreeOps;
import serverModel.ButtonException;


import serverModel.Tree;
import serverModel.TypeNotChosenException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;


public class Controller {

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    private TextField arguments;

    @FXML
    private TextField kValue;

    @FXML
    private TextField extend;

    @FXML
    private Text score;

    @FXML
    private Text printed;

    @FXML
    private Button search;

    @FXML
    private Button insert;

    @FXML
    private Button delete;

    @FXML
    private Button createButton;

    @FXML
    private Button destroy;

    private TreeOps treeOps = (TreeOps) Naming.lookup("rmi://localhost/server");

    public Controller() throws RemoteException, NotBoundException, MalformedURLException {
    }

    @FXML
    private void create() throws RemoteException {

        String treeAtTheBeggining = null;
        int k;

        List<String> parameters = asList(arguments.getText().split(" "));

        try {

            k = Integer.parseInt(kValue.getText());
            if (arguments.getText().equals("") || parameters.size() < (k / 2) || k < 3) {
                throw new ButtonException();
            }

            if (toggleButtons.getSelectedToggle() == null) {
                throw new TypeNotChosenException();
            }

            List objects = new ArrayList<>();

            switch (((ToggleButton) toggleButtons.getSelectedToggle()).getId()) {

                case "integer":
                    for (String s : parameters) {
                        objects.add(Integer.parseInt(s));
                    }
                    break;
                case "double":
                    for (String s : parameters) {
                        objects.add(Double.parseDouble(s));
                    }
                    break;
                case "string":
                    for (String s : parameters) {
                        objects.add(s);
                    }
                    break;
            }

            Collections.sort(objects);

            treeAtTheBeggining = treeOps.addOnStartup(objects, k);

            destroy.setVisible(true);
            createButton.setVisible(false);
            extend.setVisible(true);
            delete.setVisible(true);
            insert.setVisible(true);
            search.setVisible(true);


        } catch (NumberFormatException n) {
            makeAlert("parameter k has to be an int");
        } catch (ButtonException b) {
            makeAlert("not nuff arguments");
        } catch (TypeNotChosenException s) {
            makeAlert("you should first choose a type!");
        }

        printed.setText(treeAtTheBeggining);
    }

    @FXML
    private void destroy() {
        printed.setText("");
        score.setText("");
        createButton.setVisible(true);
        destroy.setVisible(false);
        extend.setVisible(false);
        extend.setText("");
        delete.setVisible(false);
        insert.setVisible(false);
        search.setVisible(false);
    }

    @FXML
    private void search() throws RemoteException {
        score.setText("");

        String isPresent = null;
        try {

            if (toggleButtons.getSelectedToggle() == null) {
                throw new ButtonException();
            }

            switch (((ToggleButton) toggleButtons.getSelectedToggle()).getId()) {
                case "integer":

                    isPresent = treeOps.search(Integer.parseInt(extend.getText()));

                    break;
                case "double":
                    isPresent = treeOps.search(Double.parseDouble(extend.getText()));

                    break;
                case "string":
                    isPresent = treeOps.search(extend.getText());
                    break;
            }


        } catch (ButtonException b) {
            makeAlert("choose type");
        } catch (NumberFormatException n) {
            score.setText("isn't here - wrong type!");
        }
        score.setText(isPresent);

    }

    @FXML
    private void insert() throws RemoteException {

        String treeAfterInsert = null;

        try {
            if (toggleButtons.getSelectedToggle() == null) {
                throw new ButtonException();
            }

            switch (((ToggleButton) toggleButtons.getSelectedToggle()).getId()) {
                case "integer":
                    treeAfterInsert = treeOps.insert(Integer.parseInt(extend.getText()));
                    break;
                case "double":
                    treeAfterInsert = treeOps.insert(Double.parseDouble(extend.getText()));
                    break;
                case "string":
                    treeAfterInsert = treeOps.insert(extend.getText());
                    break;
            }
        } catch (ButtonException b) {
            makeAlert("choose type");
        } catch (NumberFormatException n) {
            makeAlert("wrong type");
        }

        score.setText("");
        printed.setText("");
        printed.setText(treeAfterInsert);
    }

    @FXML
    private void delete() throws RemoteException {

        String isInTheTree = null;
        String treeAfterRemovingTheElement = null;
        try {
            if (toggleButtons.getSelectedToggle() == null) {
                throw new ButtonException();
            }
            switch (((ToggleButton) toggleButtons.getSelectedToggle()).getId()) {
                case "integer":
                    Integer elementInt = Integer.parseInt(extend.getText());
                    isInTheTree = treeOps.search(elementInt);
                    if (isInTheTree.equals("not present")) {
                        throw new NumberFormatException();
                    } else {
                        treeAfterRemovingTheElement = treeOps.remove(elementInt);
                    }
                    break;
                case "double":
                    Double elementDbl = Double.parseDouble(extend.getText());
                    isInTheTree = treeOps.search(elementDbl);
                    if (isInTheTree.equals("not present")) {
                        throw new NumberFormatException();
                    } else {
                        treeAfterRemovingTheElement = treeOps.remove(elementDbl);
                    }
                    break;
                case "string":
                    String elementStr = extend.getText();
                    isInTheTree = treeOps.search(elementStr);
                    if (isInTheTree.equals("not present")) {
                        throw new NumberFormatException();
                    } else {
                        treeAfterRemovingTheElement = treeOps.remove(elementStr);
                    }
                    break;
            }
        } catch (ButtonException b) {
            makeAlert("wrong type");
        } catch (NumberFormatException n) {
            makeAlert("is not present here!");
        }

        printed.setText("");
        printed.setText(treeAfterRemovingTheElement);
        score.setText("");
    }

    private void makeAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error!!");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
