package cs3500.pa05.view;

import cs3500.pa05.model.Activities.Activity;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class ActivityView extends VBox implements View {
  Label title;
  Label description;
  Label category;

  public ActivityView(Activity activity) {
    super(5);
    this.setPadding(new Insets(10));
    this.setAlignment(Pos.CENTER);
    this.title = new Label(activity.getName());
    this.description = new Label(activity.getDescription());
    this.category = new Label("<" + activity.getCategory().getName() + ">");

    this.getChildren().addAll(List.of(this.category, this.title, this.description));

    CornerRadii cornerRadii = new CornerRadii(10);
    BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
        cornerRadii, new BorderWidths(2));
    Border border = new Border(borderStroke);
    this.setBorder(border);
  }
}
