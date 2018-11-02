package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TextField txt1;

  @FXML
  private TextField txt2;

  @FXML
  private TextField txt3;

  @FXML
  private TextField txt4;

  @FXML
  private Button btn;

  @FXML
  void initialize() {
    btn.setOnAction(event -> {
      Stage stage = new Stage();
      stage.setTitle("Plot");

      MVmanager appController = new MVmanager();
      appController.printer(stage,Double.parseDouble(txt1.getText()),Double.parseDouble(txt2.getText()),Double.parseDouble(txt3.getText()),Double.parseDouble(txt4.getText()));
    });
  }
}
