package cs3500.pa05.view;

import cs3500.pa05.model.activities.Activity;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class ActivityView extends VBox {
  Label title;
  Label description;
  Label category;
  private final int width = 100;

  public ActivityView (Activity activity) {
    this.setSpacing(2);
    this.setPadding(new Insets(10));
    this.setAlignment(Pos.CENTER);
    this.title = new Label(activity.getName());
    title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 15));
    this.description = new Label(activity.getDescription());
    this.category = new Label("<" + activity.getCategory().getName() + ">");
    this.getChildren().addAll(this.title, this.category, this.description);

//    HBox categoryBox = new HBox(new Label(activity.getCategory().getName()));
//    //add a border
//    CornerRadii cornerRadii = new CornerRadii(10);
//    BorderStroke borderStroke = new BorderStroke(null, null,
//            cornerRadii, null);
//    Border border = new Border(borderStroke);
//    categoryBox.setBorder(border);
//    categoryBox.setPrefWidth(category.getWidth());
//    categoryBox.setAlignment(Pos.CENTER);
//    categoryBox.setStyle("-fx-background-color: #00FF00");

    //this.setPrefWidth(width);

    CornerRadii cornerRadii = new CornerRadii(7);
    BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
            cornerRadii, new BorderWidths(1));
    Border border = new Border(borderStroke);
    this.setBorder(border);

  }
}
