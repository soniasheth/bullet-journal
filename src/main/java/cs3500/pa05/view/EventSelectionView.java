package cs3500.pa05.view;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.Weekday;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.HBox;

public class EventSelectionView extends VBox {
  private Label event;
  private TextField eventName;
  private TextField description;
  private ComboBox categories;
  private ComboBox weekdays;
  private HBox startTime;
  private HBox endTime;
  private Button submit;


  public EventSelectionView(List<Category> categories) {
    this.event = new Label("New Event");
    this.event.setFont(Font.font("verdana", FontWeight.BOLD, 15));
    this.eventName = new TextField();
    this.description = new TextField();
    this.categories = new CategoriesView(categories);
    this.submit = new Button("Submit");
    this.weekdays = new ComboBox<>();
    this.startTime = new TimeView();
    this.endTime = new TimeView();
    this.weekdays = new WeekdayComboBox();

    this.setSpacing(10);
    this.getChildren().add(this.event);
    GridPane info = new GridPane();
    info.addRow(0, new Label("Event Name: "), this.eventName);
    info.addRow(1, new Label("Category: "), this.categories);
    info.addRow(2, new Label("Weekday:"), this.weekdays);
    info.addRow(3, new Label("Start Time: "), this.startTime);
    info.addRow(4, new Label("End Time: "), this.endTime);
    info.addRow(5, new Label("Description: "), this.description);
    this.getChildren().add(info);
    this.getChildren().add(submit);

  }
}
