package cs3500.pa05.view.activities;

import javafx.scene.control.ComboBox;

import java.time.DayOfWeek;

public class WeekdayComboBox extends ComboBox {

  public WeekdayComboBox() {
    this.getItems().addAll(
            DayOfWeek.SUNDAY.name(),
            DayOfWeek.MONDAY.name(),
            DayOfWeek.TUESDAY.name(),
            DayOfWeek.WEDNESDAY.name(),
            DayOfWeek.THURSDAY.name(),
            DayOfWeek.FRIDAY.name(),
            DayOfWeek.SATURDAY.name());
  }

  public DayOfWeek getSelectedWeekDay() {
    if (!validateAnswer()) {
      return null;
    }
    return DayOfWeek.valueOf(this.getValue().toString().toUpperCase());
  }

  private boolean validateAnswer() {
    return this.getValue() != null;
  }

  public void setDefault(DayOfWeek day) {
    this.getSelectionModel().select(day.name());
  }
}
