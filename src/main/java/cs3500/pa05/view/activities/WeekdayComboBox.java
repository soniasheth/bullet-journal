package cs3500.pa05.view.activities;

import cs3500.pa05.model.Weekday;
import javafx.scene.control.ComboBox;

public class WeekdayComboBox extends ComboBox {

  public WeekdayComboBox() {
    this.getItems().addAll(
            Weekday.SUNDAY.getRepresentation(),
        Weekday.MONDAY.getRepresentation(),
        Weekday.TUESDAY.getRepresentation(),
        Weekday.WEDNESDAY.getRepresentation(),
        Weekday.THURSDAY.getRepresentation(),
        Weekday.FRIDAY.getRepresentation(),
        Weekday.SATURDAY.getRepresentation());
  }

  public Weekday getSelectedWeekDay() {
    if (!validateAnswer()) {
      return null;
    }
    return Weekday.valueOf(this.getValue().toString().toUpperCase());
  }

  private boolean validateAnswer() {
    if (this.getValue() == null) {
      return false;
    }
    else {
      return true;
    }
  }
}
