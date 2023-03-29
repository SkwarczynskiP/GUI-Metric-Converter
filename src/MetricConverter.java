import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MetricConverter extends Application {
  private TextField tfInput = new TextField();
  private TextField tfOutput = new TextField();
  private Button btCalculate = new Button("Calculate");
  private int currentIndex;
  
  @Override
  public void start(Stage primaryStage) {
    ComboBox<String> cmbquery = new ComboBox<>();
    ObservableList<String> items = FXCollections.observableArrayList("km to mi","mi to km", "m to ft", "ft to m", "kg to lb", "lb to kg", "g to oz", "oz to g", "mm to in", "in to mm");
    cmbquery.getItems().addAll(items);
    cmbquery.setValue(items.get(0));
  
    cmbquery.setOnAction(e -> currentIndex = items.indexOf(cmbquery.getValue()));
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.add(new Label("Conversion Units:"), 0, 0);
    gridPane.add(cmbquery, 1, 0);
    gridPane.add(new Label("Input Value:"), 0, 1);
    gridPane.add(tfInput, 1, 1);
    gridPane.add(new Label("Output Value: "), 0, 2);
    gridPane.add(tfOutput, 1, 2);
    gridPane.add(btCalculate, 1, 5);

    gridPane.setAlignment(Pos.CENTER);
    tfInput.setAlignment(Pos.BOTTOM_RIGHT);
    tfOutput.setAlignment(Pos.BOTTOM_RIGHT);
    tfOutput.setEditable(false);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);
    
    
    btCalculate.setOnAction(e -> Conversion());

    Scene scene = new Scene(gridPane, 350, 250);
    primaryStage.setTitle("Metric Calculator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  private void Conversion() {
    double value = 0;
    double result = 0;
  
    try {
      value = Double.parseDouble(tfInput.getText());
    } 
    catch (Exception e) {
      Alert errorAlert = new Alert(AlertType.ERROR);
      errorAlert.setHeaderText("Input not valid");
      errorAlert.setContentText("Input must be a number");
      errorAlert.showAndWait();
    }
    
    switch(currentIndex) {
        case 0:
        result = value * 0.62137;
        break;
        case 1:
        result = value / 0.62137;
        break;
        case 2:
        result = value * 3.2808;
        break;
        case 3:
        result = value / 3.2808;
        break;
        case 4:
        result = value * 2.2046;
        break;
        case 5:
        result = value / 2.2046;
        break;
        case 6:
        result = value * 0.035274;
        break;
        case 7:
        result = value / 0.035274;
        break;
        case 8:
        result = value * 0.039370;
        break;
        case 9:
        result = value / 0.039370;
    }

    if (result < 0) {
      Alert errorAlert = new Alert(AlertType.ERROR);
      errorAlert.setHeaderText("Input not valid");
      errorAlert.setContentText("Input must be a positive number");
      errorAlert.showAndWait();
    } else {
      tfOutput.setText(String.format("%.4f",result));
    }
  }

  public static void main(String[] args) {
    launch(args);
}
}