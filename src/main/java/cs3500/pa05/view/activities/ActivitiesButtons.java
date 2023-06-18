package cs3500.pa05.view.activities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ActivitiesButtons extends VBox {
    Button event;
    Button task;

    public ActivitiesButtons() {
        this.event = new Button("Event +");
        this.task = new Button("Task +");
        this.setPrefSize(100, 100);
        this.event.setPrefSize(100,100);
        this.task.setPrefSize(100, 100);
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
