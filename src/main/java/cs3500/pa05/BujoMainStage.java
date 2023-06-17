package cs3500.pa05;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.*;
import cs3500.pa05.view.tables.TaskQueueView;
import cs3500.pa05.view.tables.WeekdayView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * my playground to test stuff
 */
public class BujoMainStage extends Application {

  private void initDummyData(WeekdayModel model) {
    model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, null, null, null));
    model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, null, null, null));
    model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, null,
        CompletionStatus.NOT_STARTED));
    model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, null,
        CompletionStatus.NOT_STARTED));
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      WeekdayModel model = new WeekdayModel();
      this.initDummyData(model);
      WeekdayView weekdayView = new WeekdayView();
      TaskQueueView taskQueueView = new TaskQueueView();
      Button btn = new Button("add new task");
      BujoController controller = new BujoController(model, weekdayView, taskQueueView, btn);
      Scene scene = new Scene(new VBox(btn, weekdayView, taskQueueView));
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void showPopup(Stage ownerStage) {
    Stage popupStage = new Stage();
    popupStage.initOwner(ownerStage);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.setTitle("Popup Window");
    Label l = new Label("haha");
    Scene popupScene = new Scene(new VBox(l));
    popupStage.setScene(popupScene);
    popupStage.showAndWait();
  }
}
