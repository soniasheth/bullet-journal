package cs3500.pa05;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.*;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.activities.ActivitiesButtons;
import cs3500.pa05.view.tables.TaskQueueView;
import cs3500.pa05.view.tables.WeekdaysView;
import java.time.LocalTime;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

/**
 * my playground to test stuff
 */
public class BujoMainStage extends Application {
  private int height = 1000;
  private int width = 2000;

  private void initDummyData(WeekdaysModel model) {
    Settings.reset();
    List<Category> c = Settings.getInstance().getCategories();
    c.addAll(List.of(new Category("Work", Color.RED), new Category("School", Color.CYAN), new Category("Fun", Color.PINK)));
    model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, c.get(3), LocalTime.of(11, 30), LocalTime.of(18, 00)));
    model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, c.get(3), LocalTime.of(20, 00), LocalTime.of(23, 45)));
    model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, c.get(2),
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, c.get(1),
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cry", "yes", Weekday.TUESDAY, c.get(1), CompletionStatus.COMPLETED));
    model.addActivity(new Task("pa05", "is hard", Weekday.FRIDAY, c.get(3), CompletionStatus.IN_PROGRESS));
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      WeekdaysModel model = new WeekdaysModel();
      this.initDummyData(model);

      //int views needed for the bujo
      ActivitiesButtons addActivities = new ActivitiesButtons();
      Button settings = new Button("Settings");
      Button save = new Button("Save");
      WeekdaysView weekdaysView = new WeekdaysView();
      TaskQueueView taskQueueView = new TaskQueueView();
      Button eventStats = new Button("Event Stats");
      Button taskStats = new Button("Task Stats");
      eventStats.setPrefSize(200,50);
      taskStats.setPrefSize(200, 50);
      BujoView bujo = new BujoView(addActivities, settings, save, weekdaysView, taskQueueView, eventStats, taskStats);

      //init the controller
      BujoController controller = new BujoController(primaryStage, model, weekdaysView, taskQueueView, addActivities, settings, eventStats, taskStats);
      //controller.welcome();

      //show the scene
      Scene scene = new Scene(bujo);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
