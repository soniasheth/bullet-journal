package cs3500.pa05.view;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {

  @Override

  public void start(Stage primaryStage) throws Exception {

    SettingsView v = new SettingsView(primaryStage);



    Scene scene = new Scene(v);

    primaryStage.setScene(scene);

    primaryStage.show();

  }

}