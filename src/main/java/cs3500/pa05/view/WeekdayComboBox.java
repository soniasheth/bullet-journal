package cs3500.pa05.view;

import cs3500.pa05.model.Weekday;
import javafx.scene.control.ComboBox;

public class WeekdayComboBox extends ComboBox {

  public WeekdayComboBox() {
    this.getItems().addAll(
        Weekday.MONDAY.getRepresentation(),
        Weekday.TUESDAY.getRepresentation(),
        Weekday.WEDNESDAY.getRepresentation(),
        Weekday.THURSDAY.getRepresentation(),
        Weekday.FRIDAY.getRepresentation(),
        Weekday.SATURDAY.getRepresentation());
  }
}
