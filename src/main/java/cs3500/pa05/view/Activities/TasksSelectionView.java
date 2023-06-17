package cs3500.pa05.view.Activities;

import cs3500.pa05.model.*;
import cs3500.pa05.view.FormDelegate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class TasksSelectionView extends VBox {
    private final Label event = new Label("New Task");
    private TextField taskName;
    private TextField description;
    private CategoriesView categories;
    private WeekdayComboBox weekdays;
    private Button submit;
    private FormDelegate submitDelegate;

    public TasksSelectionView (FormDelegate delegate, List<Category> categories) {
        //elements on the event pop up
        this.event.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        this.taskName = new TextField();
        this.description = new TextField();
        this.categories = new CategoriesView(categories);
        this.submit = new Button("Submit");
        this.weekdays = new WeekdayComboBox();
        this.submitDelegate = delegate;
        this.setSpacing(10);

        //add the title
        this.getChildren().add(this.event);

        //create the grid with all the other elements
        GridPane info = new GridPane();
        info.addRow(0, new Label("Task Name: "), this.taskName);
        info.addRow(1, new Label("Category: "), this.categories);
        info.addRow(2, new Label("Weekday:"), this.weekdays);
        info.addRow(5, new Label("Description: "), this.description);

        //add submit button and grid to the vbox
        HBox hbox = new HBox();
        hbox.getChildren().add(submit);
        hbox.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        HBox.setHgrow(submit, Priority.ALWAYS);
        this.getChildren().add(info);
        this.getChildren().add(hbox);

        //when the submit button is pressed
        submit.setOnAction(e -> submitHandling());
    }

    public void submitHandling() {
        Activity task = new Task(
                this.taskName.getText(),
                this.description.getText(),
                this.weekdays.getSelectedWeekDay(),
                this.categories.getChosenCategory(),
                CompletionStatus.NOT_STARTED);


        //calls the submit method of the delegate and then adds it to the model
        //this.submitDelegate.submit(event);
        //System.out.println(event.toString());
        //return event;
    }
}
