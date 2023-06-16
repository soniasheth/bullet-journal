package cs3500.pa05.view;

import cs3500.pa05.Utils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SettingsView extends VBox {
  String name;

  String email;
  int maxActivities; //change later, null???

  TextField nameInput;
  TextField emailInput;
  TextField numInput;

  SettingsView(Stage primaryStage) {
    primaryStage.setTitle("Welcome!");

    nameInput = new TextField();
    emailInput = new TextField();
    numInput = new TextField();

    Button button = new Button("Submit");

    Label nameLabel = new Label("Name: ");
    Label emailLabel = new Label("Email: ");
    Label numLabel = new Label("Max Activities: ");

    Text text = new Text("");
    button.setOnAction(e -> getUserInput(text));

    HBox box = new HBox(10);
    box.getChildren().addAll(nameLabel, nameInput, emailLabel, emailInput, numLabel, numInput);

    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(10));
    this.getChildren().addAll(box, button, text);


  }

  private void getUserInput(Text text) {
    this.name = nameInput.getText();
    this.email = emailInput.getText();
    String number = numInput.getText();
    if (isValidNumber(number)) {
      this.maxActivities = Integer.parseInt(number);
    } else {
      Utils.showAlert("Invalid Input", "Please enter a valid number");
    }
    text.setText("Hello " + name + ". Welcome to your Bullet Journal.");
  }


  private boolean isValidNumber(String input) {
    try {
      int num = Integer.parseInt(input);
      return num >= 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

/*
  TODO:
   verify number inputs, -- make sure this allows for re-entering (especially to make sure not null)
   verify email input,
   decrease size of number Hbox,
   add buffer between things HBoxes
   add functionality to make items editable
 */
}
