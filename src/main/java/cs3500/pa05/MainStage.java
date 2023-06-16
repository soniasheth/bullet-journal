package cs3500.pa05;

import cs3500.pa05.view.EventSelectionView;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      EventSelectionView v = new EventSelectionView();
      Scene scene = new Scene(v);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch(Exception e) {
      e.printStackTrace();
    }

  }
}
