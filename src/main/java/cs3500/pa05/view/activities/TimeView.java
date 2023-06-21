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

    this.hour.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    this.minute.getItems().addAll("00", "15", "30", "45");
    this.amPm.getItems().addAll("AM", "PM");

    this.getChildren().addAll(this.hour, this.minute, this.amPm);
  }

  public LocalTime getTime() {
    if (!validateAnswer()) {
      return null;
    }
    else {
      int hour = Integer.parseInt(this.hour.getValue().toString());
      int minutes = Integer.parseInt(this.minute.getValue().toString());
      if (this.amPm.getValue().toString().equals("PM") && hour != 12) {
        hour += 12;
      } else if (this.amPm.getValue().toString().equals("AM") && hour == 12) {
        hour = 0;
      }
      return LocalTime.of(hour, minutes);
    }
  }

  private boolean validateAnswer() {
    if (this.hour.getValue() == null || this.minute.getValue() == null || this.amPm.getValue() == null) {
      return false;
    }
    else {
      return true;
    }
  }

  public void setDefault(LocalTime time) {
    if (time.getHour() > 12) {
      this.hour.getSelectionModel().select(Integer.toString(time.getHour() - 12));
      this.amPm.getSelectionModel().select("PM");
    } else {
      this.hour.getSelectionModel().select(Integer.toString(time.getHour()));
      this.amPm.getSelectionModel().select("AM");
    }
    this.minute.getSelectionModel().select(Integer.toString(time.getMinute()));
  }
}
