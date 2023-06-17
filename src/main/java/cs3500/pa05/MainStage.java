package cs3500.pa05;

import cs3500.pa05.model.Category;
import cs3500.pa05.view.EventSelectionView;
import java.io.IOException;
import java.util.ArrayList;
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
      EventSelectionView v = new EventSelectionView(list);
      Scene scene = new Scene(v);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch(Exception e) {
      e.printStackTrace();
    }

  }
}
