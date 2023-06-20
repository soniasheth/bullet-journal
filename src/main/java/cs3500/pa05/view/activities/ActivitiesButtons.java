package cs3500.pa05.view.activities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ActivitiesButtons extends HBox {
    Button event;
    Button task;

    public ActivitiesButtons() {
        this.event = new Button("Event +");
        this.event.setPrefSize(150,40);
        this.event.setStyle("-fx-background-color: #8FBC8F;");

        this.task = new Button("Task +");
        this.task.setPrefSize(150, 40);
        this.task.setStyle("-fx-background-color: #8FBC8F;");

        this.setPrefSize(150, 40);
        this.setSpacing(10);
        this.getChildren().add(event);
        this.getChildren().add(task);
    }

    public void setOnActionEventButton(EventHandler<ActionEvent> action) {
        this.event.setOnAction(action);
    }

    public void setOnActionTaskButton(EventHandler<ActionEvent> action) {
        this.task.setOnAction(action);
    }


}
