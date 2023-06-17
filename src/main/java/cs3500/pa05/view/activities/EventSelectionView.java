package cs3500.pa05.view.activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.Event;
import java.util.List;

import cs3500.pa05.view.delegates.FormDelegate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Represents the Event Pop Up
 */

public class EventSelectionView extends VBox {
  //fields and elements on the event pop up
  private final Label event = new Label("New Event");
  private TextField eventName;
  private TextField description;
  private CategoriesView categories;
  private WeekdayComboBox weekdays;
  private TimeView startTime;
  private TimeView endTime;
  private Button submit;

  private FormDelegate submitDelegate;


  public EventSelectionView (FormDelegate delegate, List<Category> categories) {
    //elements on the event pop up
    this.event.setFont(Font.font("verdana", FontWeight.BOLD, 15));
    this.eventName = new TextField();
    this.description = new TextField();
    this.categories = new CategoriesView(categories);
    this.submit = new Button("Submit");
    this.startTime = new TimeView();
    this.endTime = new TimeView();
    this.weekdays = new WeekdayComboBox();
    this.submitDelegate = delegate;
    this.setSpacing(10);

    //add the title
    this.getChildren().add(this.event);

    //create the grid with all the other elements
    GridPane info = new GridPane();
    info.addRow(0, new Label("Event Name: "), this.eventName);
    info.addRow(1, new Label("Category: "), this.categories);
    info.addRow(2, new Label("Weekday:"), this.weekdays);
    info.addRow(3, new Label("Start Time: "), this.startTime);
    info.addRow(4, new Label("End Time: "), this.endTime);
    info.addRow(5, new Label("Description: "), this.description);

    //add submit button and grid to the vbox
    HBox hbox = new HBox();
    hbox.getChildren().add(submit);
    hbox.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
    HBox.setHgrow(submit, Priority.ALWAYS);
    this.getChildren().add(info);
    this.getChildren().add(hbox);

    //when the submit button is pressed
    submit.setOnAction(e -> submitHandling());
  }

  public void submitHandling() {
    Event event = new Event(
            this.eventName.getText(),
            this.description.getText(),
            this.weekdays.getSelectedWeekDay(),
            this.categories.getChosenCategory(),
            this.startTime.getTime(),
            this.endTime.getTime());

    //calls the submit method of the delegate and then adds it to the model
    //this.submitDelegate.submit(event);
    //System.out.println(event.toString());
    //return event;
  }

}
