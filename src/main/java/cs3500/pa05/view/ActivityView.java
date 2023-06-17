package cs3500.pa05.view;

import cs3500.pa05.model.Activity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class ActivityView extends VBox {

  Label title;
  Label description;

  public ActivityView(Activity activity) {
    super(5);
    this.setPadding(new Insets(10));
    this.setAlignment(Pos.CENTER);
    this.title = new Label(activity.getName());
    this.description = new Label(activity.getDescription());

    this.getChildren().addAll(List.of(this.title, this.description));
  }
}
