package cs3500.pa05.view.activities;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.time.LocalTime;

public class TimeView extends HBox {

  private ComboBox hour;
  private ComboBox minute;
  private ComboBox amPm;

  public TimeView() {
    this.hour = new ComboBox<>();
    this.minute = new ComboBox<>();
    this.amPm = new ComboBox<>();

    this.hour.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    this.minute.getItems().addAll("00", 15, 30, 45);
    this.amPm.getItems().addAll("AM", "PM");

    this.getChildren().addAll(this.hour, this.minute, this.amPm);
  }

  public LocalTime getTime() {
    int hour = Integer.parseInt(this.hour.getValue().toString());
    int minutes = Integer.parseInt(this.minute.getValue().toString());
    if(this.amPm.getValue().toString().equals("PM")) {
      hour+=12;
    }
    return LocalTime.of(hour, minutes);
  }
}
