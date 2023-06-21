package cs3500.pa05.view;

import cs3500.pa05.Utils;
import cs3500.pa05.model.Settings;
import cs3500.pa05.view.delegates.FormDelegate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class SettingsView extends VBox implements FormView {
  TextField nameInput;
  TextField emailInput;
  TextField eventInput;
  TextField taskInput;
  Button submitButton;
  Settings settings;

  FormDelegate submitDelegate;

  private DatePicker datePicker;

  boolean welcome;

  public SettingsView(Settings setting, FormDelegate delegate, Stage primaryStage, boolean welcome) {
    this.nameInput = new TextField();
    this.emailInput = new TextField();
    this.eventInput = new TextField();
    this.taskInput = new TextField();
    this.settings = setting;
    this.datePicker = new DatePicker();


    this.submitDelegate = delegate;
    this.setSpacing(10);

    this.welcome = welcome;

    if (welcome) {
      this.submitButton = new Button("Submit");
    }
    else {
      this.submitButton = new Button("Save");
      this.datePicker.setDisable(true);
    }

    if (!welcome) {
      //set default text
      this.nameInput.setText(this.settings.getName());
      this.emailInput.setText(this.settings.getEmail());
      this.eventInput.setText(Integer.toString(this.settings.getEventMax()));
      this.taskInput.setText(Integer.toString(this.settings.getTaskMax()));
      this.datePicker.setValue(this.settings.getLocalDate());
    }

    //add the type boxes to the vbox
    GridPane settings = new GridPane();
    settings.addRow(0, new Label("Name: "), nameInput);
    settings.addRow(1, new Label("Email: "), emailInput);
    settings.addRow(2, new Label("Max Events: "), eventInput);
    settings.addRow(3, new Label("Max Tasks: "), taskInput);
    settings.addRow(4, new Label("Select a week: "), datePicker);

    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(10));
    this.getChildren().addAll(settings);
    this.getChildren().addAll(submitButton);


    //set an on action event
    submitButton.setOnAction(event -> {
      try {
        getUserInput();
        primaryStage.close();
        this.submitDelegate.submit(this, Settings.getInstance());
      } catch (IllegalArgumentException e) {
        Utils.showAlert("Warning!", e.getMessage());
      }
    });
  }

  private void getUserInput() {
    //gets the user's name
    this.settings.setName(nameInput.getText());

    //gets the user entered max event
    String events = eventInput.getText();
    if (Utils.isValidNumber(events)) {
      int eventMax = Integer.parseInt(events);
      this.settings.setEventMax(eventMax);
    } else {
      throw new IllegalArgumentException("Please enter a valid event number.");
    }

    //get user's entered max tasks
    String tasks = taskInput.getText();
    if (Utils.isValidNumber(tasks)) {
      int taskMax = Integer.parseInt(tasks);
      this.settings.setTaskMax(taskMax);
    } else {
      throw new IllegalArgumentException("Please enter a valid task number.");
    }

    //gets user's email
    String email = emailInput.getText();
    if (Utils.isValidEmail(email)) {
      this.settings.setEmail(email);
    } else {
      throw new IllegalArgumentException("Please enter a valid email.");
    }


    //get the week
    if (welcome) {
      LocalDate selectedDate = datePicker.getValue();
      if (selectedDate != null) {
        this.settings.setLocalDate(selectedDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String dateString = selectedDate.format(formatter);

        DayOfWeek dayOfWeek = selectedDate.getDayOfWeek();

        this.settings.setDateString(dateString);
        this.settings.setStartDay(dayOfWeek);

        System.out.println("selected week: " + dateString);
        System.out.println("day of week: " + dayOfWeek);

        System.out.println("settings: " + Settings.getInstance().getDateString() + Settings.getInstance().getDaysOfWeek());
      } else {
        throw new IllegalArgumentException("Select a week!");
      }
    }

  }

}
