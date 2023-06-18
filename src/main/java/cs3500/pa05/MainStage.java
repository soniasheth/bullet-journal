package cs3500.pa05;

import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.Category;

import java.util.ArrayList;


import cs3500.pa05.view.activities.ActivitySelectionView;
import cs3500.pa05.view.activities.AddButton;
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
      //ActivitySelectionView v = new ActivitySelectionView(ActivityType.TASK, list, null);
      AddButton v = new AddButton();
      Scene scene = new Scene(v);
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch(Exception e) {
      e.printStackTrace();
    }

  }
}