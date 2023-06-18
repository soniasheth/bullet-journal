package cs3500.pa05.view;

import cs3500.pa05.Utils;
import cs3500.pa05.model.Settings;
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

import java.util.ArrayList;
import java.util.Arrays;


public class SettingsView extends VBox {
  TextField nameInput;
  TextField emailInput;
  TextField eventInput;

  TextField taskInput;

  SettingsView(Settings setting, boolean welcome, Stage primaryStage) {

    nameInput = new TextField();
    emailInput = new TextField();
    eventInput = new TextField();
    taskInput = new TextField();

    Button button;
    if (welcome) {
      button = new Button("Submit");
    } else {
      button = new Button("Save");
    }

    Label nameLabel = new Label("Name: ");
    Label emailLabel = new Label("Email: ");
    Label eventLabel = new Label("Max Events: ");
    Label taskLabel = new Label("Max Tasks: ");

    ArrayList<HBox> hboxs = new ArrayList<>(Arrays.asList(new HBox(10), new HBox(10), new HBox(10), new HBox(10)));
    hboxs.get(0).getChildren().addAll(nameLabel, nameInput);
    hboxs.get(1).getChildren().addAll(emailLabel, emailInput);
    hboxs.get(2).getChildren().addAll(eventLabel, eventInput);
    hboxs.get(3).getChildren().addAll(taskLabel, taskInput);


    Text text = new Text("");
    button.setOnAction(event -> {
      try {
        getUserInput(setting, text);
        primaryStage.close();
      } catch (IllegalArgumentException e) {
        Utils.showAlert("Warning", e.getMessage());
      }

    });

    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(10));

    this.getChildren().addAll(hboxs);
    this.getChildren().addAll(button, text);


  }

  private void getUserInput(Settings setting, Text text) {
    setting.setName(nameInput.getText());

    String events = eventInput.getText();
    if (Utils.isValidNumber(events)) {
      int eventMax = Integer.parseInt(events);
      setting.setEventMax(eventMax);
    } else {
      throw new IllegalArgumentException("Please enter a valid event number");
    }

    String tasks = taskInput.getText();
    if (Utils.isValidNumber(tasks)) {
      int taskMax = Integer.parseInt(tasks);
      setting.setTaskMax(taskMax);
    } else {
      throw new IllegalArgumentException("Please enter a valid task number");
    }

    String email = emailInput.getText();
    if (Utils.isValidEmail(email)) {
      setting.setEmail(email);
    } else {
      throw new IllegalArgumentException("Please enter a valid email");
    }

    text.setText("Hello " + setting.getName() + ". Welcome to your Bullet Journal.");

  }



/*
  TODO:
   verify number inputs, -- make sure this allows for re-entering (especially to make sure not null)
   verify email input,
   decrease size of number Hbox,
   add buffer between things HBoxes
   add functionality to make items editable

   welcome flag:

   if welcome is true:

   Welcome
   name: textbox
   .....
   welcome to your Bullet Journal


   if welcome to false:

   Setting
   name: Maggie (set as textfield)

   Settings changed!
 */
}
