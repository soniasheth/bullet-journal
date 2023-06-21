package cs3500.pa05;

import cs3500.pa05.model.Category;

import java.time.LocalTime;
import java.util.ArrayList;


import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.MiniViewer;
import cs3500.pa05.view.activities.ActivitiesButtons;
import cs3500.pa05.view.activities.ActivitySelectionView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainStage extends Application {
  @Override
  public void start(Stage primaryStage) {

    try {
      Category c1 = new Category("School", Color.RED);
      Category c2 = new Category("Home", Color.RED);
      ArrayList<Category> list = new ArrayList<>();
      list.add(c1);
      list.add(c2);

      Activity event = new Event("OOD", "hard work", Weekday.SATURDAY, c1, LocalTime.of(13, 45), LocalTime.of(14, 30));
      Activity task = new Task("Annoying", "help", Weekday.SUNDAY, c2, CompletionStatus.COMPLETED);
      ActivitySelectionView v = new ActivitySelectionView(event, list, null, null);
      //MiniViewer d = new MiniViewer(event);
      Scene scene = new Scene(v);
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch(Exception e) {
      e.printStackTrace();
    }

  }
}


