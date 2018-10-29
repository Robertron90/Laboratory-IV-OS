package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class Controller {

    @FXML
    private ResourceBundle resourceBundle;

    @FXML
    private URL location;

    @FXML
    private TextField firstText;

    @FXML
    private TextField secondText;

    @FXML
    private TextField thirdText;

    @FXML
    private TextField fourthText;

    @FXML
    private Button buttonSetter;

    @FXML
    void initialize() {
        buttonSetter.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Plot");

            MVmanager mVmanager = new MVmanager();
            mVmanager.printer(stage,Double.parseDouble(firstText.getText()),Double.parseDouble(secondText.getText()),Double.parseDouble(thirdText.getText()),Double.parseDouble(fourthText.getText()));
        });
    }
}
