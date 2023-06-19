package cs3500.pa05.view.activities;

import cs3500.pa05.Utils;
import cs3500.pa05.model.*;
import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.delegates.FormDelegate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class ActivitySelectionView extends VBox implements FormView {
    private Label label;
    private TextField name;
    private TextField description;
    private CategoriesView categories;
    private WeekdayComboBox weekdays;
    private TimeView startTime;
    private TimeView endTime;
    private Button submit;
    private FormDelegate submitDelegate;
    private ActivityType activityType;



    public ActivitySelectionView(ActivityType type, List<Category> categories, FormDelegate delegate, Stage popupStage) {
        //elements on the event pop up
        this.activityType = type;
        this.name = new TextField();
        this.description = new TextField();
        this.categories = new CategoriesView(categories);
        this.submit = new Button("Submit");
        this.weekdays = new WeekdayComboBox();
        this.submitDelegate = delegate;
        this.setSpacing(10);

        GridPane info;
        if (type.equals(ActivityType.EVENT)) {
            info = eventPopUp();
        } else {
            info = taskPopUp();
        }
        //change font
        this.label.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        //add the title
        this.getChildren().add(this.label);
        //add gridpane
        this.getChildren().add(info);
        //add submit button
        HBox hbox = new HBox();
        hbox.getChildren().add(submit);
        hbox.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        HBox.setHgrow(submit, Priority.ALWAYS);
        this.getChildren().add(hbox);

        //when the submit button is pressed
        submit.setOnAction(event -> {
            try {
                Activity activity = submitHandling();
                popupStage.close();
                this.submitDelegate.submit(this, activity);
            } catch (IllegalArgumentException e) {
                Utils.showAlert("Warning", e.getMessage());

            }
        });
    }

    private GridPane eventPopUp() {
        //event specific calls
        this.label = new Label("New Event");
        this.startTime = new TimeView();
        this.endTime = new TimeView();

        //make the grid pane
        GridPane info = new GridPane();
        info.addRow(0, new Label("Event Name: "), this.name);
        info.addRow(1, new Label("Category: "), this.categories);
        info.addRow(2, new Label("Weekday:"), this.weekdays);
        info.addRow(3, new Label("Start Time: "), this.startTime);
        info.addRow(4, new Label("End Time: "), this.endTime);
        info.addRow(5, new Label("Description: "), this.description);
        return info;
    }

    private GridPane taskPopUp() {
        this.label = new Label("New Task");
        GridPane info = new GridPane();
        info.addRow(0, new Label("Task Name: "), this.name);
        info.addRow(1, new Label("Category: "), this.categories);
        info.addRow(2, new Label("Weekday:"), this.weekdays);
        info.addRow(5, new Label("Description: "), this.description);
        return info;
    }

    public Activity submitHandling() {
        Activity activity;
        if (!validateAnswers()) {

            throw new IllegalArgumentException("You must fill out all the information!");

        }
        else {
            if (this.activityType.equals(ActivityType.EVENT)) {
                activity = new Event(
                        this.name.getText(),
                        this.description.getText(),
                        this.weekdays.getSelectedWeekDay(),
                        this.categories.getChosenCategory(),
                        this.startTime.getTime(),
                        this.endTime.getTime());
            }
            else {
                activity = new Task(
                        this.name.getText(),
                        this.description.getText(),
                        this.weekdays.getSelectedWeekDay(),
                        this.categories.getChosenCategory(),
                        CompletionStatus.NOT_STARTED);
            }

            //calls the submit method of the delegate and then adds it to the model

            return activity;
        }

    }

    private boolean validateAnswers() {
        boolean validated = !this.name.equals("") && this.weekdays.getSelectedWeekDay() != null &&
                this.categories.getChosenCategory() != null;
        if (this.activityType.equals(ActivityType.EVENT)) {
            if (this.startTime == null || this.endTime == null) {
                validated = false;
            }
        }

        return validated;
    }

}
