package cs3500.pa05;

import cs3500.pa05.controller.JeffreyWangController;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.TaskQueueView;
import cs3500.pa05.view.TimeView;
import cs3500.pa05.view.WeekdayView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalTime;

/**
 * my playground to test stuff
 */
public class JeffreyWangMainStage extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      WeekdayView weekdayView = new WeekdayView();
      TaskQueueView taskQueueView = new TaskQueueView();
      Button btn = new Button("add new task");
      btn.setOnAction(event -> {
        this.showPopup(primaryStage);
      });
      JeffreyWangController controller = new JeffreyWangController(weekdayView, taskQueueView, btn);
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