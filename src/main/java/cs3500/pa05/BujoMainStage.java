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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
  private int width = 1000;

  private void initDummyData(WeekdayModel model) {
    model.getCategories().addAll(List.of(new Category("Work", null), new Category("School", null), new Category("Fun", null)));
    model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, model.getCategories().get(3), null, null));
    model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, model.getCategories().get(3), null, null));
    model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, model.getCategories().get(2),
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, model.getCategories().get(1),
        CompletionStatus.NOT_STARTED));
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      WeekdayModel model = new WeekdayModel();
      this.initDummyData(model);

      int prefWidth = 1000;
      int prefHeight = 400;
      //elements needed for the whole grid
      BorderPane bujo = new BorderPane();
      bujo.setPrefSize(prefWidth, prefHeight);
      bujo.setPadding(new Insets(20, 20, 20, 20));

      //buttons needed
      ActivitiesButtons addActivities = new ActivitiesButtons();
      Button settings = new Button("Settings");
      Button save = new Button("Save");

      //week of label
      HBox weekOfLabel = new HBox();
//      weekOfLabel.setBorder
//              (new Border
//                      (new BorderStroke
//                              (Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
      Text weekOf = new Text("Week of 6/1 - 6/8");
      weekOf.setFont(Font.font("verdana", FontWeight.BOLD, 20));
      weekOfLabel.getChildren().add(weekOf);

      //larger elements needed
      WeekdayView weekdayView = new WeekdayView();
      TaskQueueView taskQueueView = new TaskQueueView();

      //put them all together
      VBox left = new VBox(addActivities, taskQueueView);
      left.setSpacing(20);

      //set the top
      HBox btn = new HBox(settings, save);
      btn.setSpacing(15);
      HBox top = new HBox(weekOfLabel, btn);
      top.setSpacing(prefWidth - 200);
      bujo.setTop(top);
      BorderPane.setMargin(weekOfLabel, new Insets(10, 10, 100, 10));

      //set the left
      bujo.setLeft(left);
      BorderPane.setMargin(left, new Insets(10, 10, 10, 10));
      //set the center
      bujo.setCenter(weekdayView);

      //init the controller
      BujoController controller = new BujoController(primaryStage, model, weekdayView, taskQueueView, addActivities, settings);

      //show the scene
      Scene scene = new Scene(bujo);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
