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
import cs3500.pa05.view.tables.WeekdayView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

/**
 * my playground to test stuff
 */
public class BujoMainStage extends Application {
  private int height = 1000;
  private int width = 2000;

  private void initDummyData(WeekdayModel model) {
    List<Category> c = Settings.getInstance().getCategories();
    c.addAll(List.of(new Category("Work", null), new Category("School", null), new Category("Fun", null)));
    model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, c.get(3), null, null));
    model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, c.get(3), null, null));
    model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, c.get(2),
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, c.get(1),
        CompletionStatus.NOT_STARTED));
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      WeekdayModel model = new WeekdayModel();
      this.initDummyData(model);

      //int views needed for the bujo
      ActivitiesButtons addActivities = new ActivitiesButtons();
      Button settings = new Button("Settings");
      Button save = new Button("Save");
      WeekdayView weekdayView = new WeekdayView();
      TaskQueueView taskQueueView = new TaskQueueView();
      //put all together in the bujo view
      BujoView bujo = new BujoView(addActivities, settings, save, weekdayView, taskQueueView);

      //init the controller
      BujoController controller = new BujoController(primaryStage, model, weekdayView, taskQueueView, addActivities, settings);

      //show the scene
      Scene scene = new Scene(bujo, width, height);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
