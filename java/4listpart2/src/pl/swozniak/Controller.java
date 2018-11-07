package pl.swozniak;

/*@author: Stanisław Woźniak
@title: Controller   */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class Controller {

    @FXML
    private TextField enter;
    @FXML
    private Text score;

    @FXML
    private void handleClickAction() throws IOException{ //enter parameters and print score on scene

        score.setText("");

        ArrayList<String> output =new ArrayList<>();
        output.add("./main");
        output.addAll(asList(enter.getText().split(" ")));

        String[] outputArray = output.toArray(new String[0]);
        InputStream stream = Runtime.getRuntime().exec(outputArray).getInputStream();
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        while((line= bufferedReader.readLine()) != null){
            score.setText(score.getText() + line + '\n');
        }
        score.setVisible(true);
    }
}
