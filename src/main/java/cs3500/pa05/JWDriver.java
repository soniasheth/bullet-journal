package cs3500.pa05;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.PersistenceManager;
import cs3500.pa05.model.Settings;
import cs3500.pa05.model.WeekdaysModel;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.model.enums.Weekday;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import javafx.scene.paint.Color;

public class JWDriver {

  private static void initDummyData(WeekdaysModel model) {
    Settings.reset();
    List<Category> c = Settings.getInstance().getCategories();
    c.addAll(List.of(new Category("None", Color.WHITE), new Category("Work", Color.WHITE), new Category("School", Color.WHITE), new Category("Fun", Color.WHITE)));
    model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, c.get(3), LocalTime.of(11, 3),
        LocalTime.of(18, 00)));
    model.addActivity(
        new Event("movie night", "fun", Weekday.WEDNESDAY, c.get(3), LocalTime.of(20, 00),
            LocalTime.of(23, 45)));
    model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, c.get(2),
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, c.get(1),
        CompletionStatus.NOT_STARTED));
    model.addActivity(
        new Task("cry", "yes", Weekday.TUESDAY, c.get(1), CompletionStatus.COMPLETED));
    model.addActivity(
        new Task("pa05", "is hard", Weekday.FRIDAY, c.get(3), CompletionStatus.IN_PROGRESS));
  }

  public static void main(String[] args) throws IOException {

  }
}

