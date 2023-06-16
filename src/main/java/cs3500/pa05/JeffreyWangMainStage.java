package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JeffreyWangController;
import cs3500.pa05.view.WeekdayView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * my playground to test stuff
 */
public class JeffreyWangMainStage extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            WeekdayView view = new WeekdayView();
            Controller controller = new JeffreyWangController(view);
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}