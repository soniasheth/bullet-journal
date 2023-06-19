package cs3500.pa05;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.*;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
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
import javafx.scene.layout.*;
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
    List<Category> c = Settings.getInstance().getCategories();
    c.addAll(List.of(new Category("Work", null), new Category("School", null), new Category("Fun", null)));
    model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, c.get(3), null, null));
    model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, c.get(3), null, null));
    model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, c.get(2),
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, c.get(1),
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cry", "yes", Weekday.TUESDAY, c.get(1), CompletionStatus.COMPLETED));
    model.addActivity(new Task("pa05", "is hard", Weekday.FRIDAY, c.get(3), CompletionStatus.IN_PROGRESS));
  }

  @Override
  public void start(Stage primaryStage) {
    List<String> fontFamilies = Font.getFamilies();

    // Print all the font families
    for (String fontFamily : fontFamilies) {
      System.out.println(fontFamily);
    }
    try {
      WeekdayModel model = new WeekdayModel();
      this.initDummyData(model);

      //elements needed for the whole grid
      BorderPane bujo = new BorderPane();
      bujo.setPadding(new Insets(20, 20, 20, 20));

      //buttons needed
      ActivitiesButtons addActivities = new ActivitiesButtons();
      Button settings = new Button("Settings");
      Button save = new Button("Save");

      Button eventStats = new Button("Event Stats");
      Button taskStats = new Button("Task Stats");

      //week of label
      HBox weekOfLabel = new HBox();

//      weekOfLabel.setBorder
//              (new Border
//                      (new BorderStroke
//                              (Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
      Text weekOf = new Text("Week of " + Settings.getInstance().getWeek());

      weekOf.setFont(Font.font("Bradley Hand", FontWeight.BOLD, 30));

      weekOfLabel.getChildren().add(weekOf);

      //larger elements needed
      WeekdayView weekdayView = new WeekdayView();
      TaskQueueView taskQueueView = new TaskQueueView();


      //put them all together
      VBox left = new VBox(addActivities, taskQueueView, eventStats, taskStats);
      left.setSpacing(20);


      //set the top
      HBox btn = new HBox(settings, save);
      btn.setSpacing(15);
      HBox top = new HBox(weekOfLabel, btn);
      top.setPrefWidth(width);
      btn.setAlignment(Pos.TOP_RIGHT);
      bujo.setTop(top);
      bujo.setMargin(top, new Insets(10, 10, 10, 10));

      //set the left
      VBox left = new VBox(addActivities, taskQueueView);
      left.setSpacing(20);
      bujo.setLeft(left);
      BorderPane.setMargin(left, new Insets(20, 20, 20, 20));

      //set the center
      bujo.setCenter(weekdayView);
      bujo.setAlignment(weekdayView, Pos.TOP_CENTER);

      //init the controller
      BujoController controller = new BujoController(primaryStage, model, weekdayView, taskQueueView, addActivities, settings, eventStats, taskStats);

      //show the scene
      Scene scene = new Scene(bujo,width,height);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
