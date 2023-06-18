package cs3500.pa05;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.*;
import cs3500.pa05.model.Activities.Event;
import cs3500.pa05.model.Activities.Task;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.activities.ActivitiesButtons;
import cs3500.pa05.view.tables.TaskQueueView;
import cs3500.pa05.view.tables.WeekdayView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * my playground to test stuff
 */
public class BujoMainStage extends Application {

  private void initDummyData(WeekdayModel model) {
    model.getCategories().addAll(List.of(new Category("Work", null), new Category("School", null), new Category("Other", null)));
    model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, model.getCategories().get(2), null, null));
    model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, model.getCategories().get(2), null, null));
    model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, model.getCategories().get(1),
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, model.getCategories().get(0),
        CompletionStatus.NOT_STARTED));
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      WeekdayModel model = new WeekdayModel();
      this.initDummyData(model);
      WeekdayView weekdayView = new WeekdayView();
      TaskQueueView taskQueueView = new TaskQueueView();
      ActivitiesButtons addActivities = new ActivitiesButtons();
      Button settings = new Button("Settings");
      BujoController controller = new BujoController(primaryStage, model, weekdayView, taskQueueView, addActivities, settings);
      Scene scene = new Scene(new VBox(addActivities, settings, weekdayView, taskQueueView));
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
