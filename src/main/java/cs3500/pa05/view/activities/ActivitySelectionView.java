package cs3500.pa05.view.activities;

import cs3500.pa05.Utils;
import cs3500.pa05.model.*;
import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Event;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.delegates.FormDelegate;
import cs3500.pa05.view.tables.TaskQueueView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private Label event;
    private TextField name;
    private TextField description;
    private CategoriesView categories;
    private WeekdayComboBox weekdays;
    private TimeView startTime;
    private TimeView endTime;
    private ComboBox completion;
    private Button submit;
    private FormDelegate submitDelegate;
    private ActivityType activityType;
    private Activity activity;

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
            this.activity = new Event();
            info = eventPopUp();
        } else {
            info = taskPopUp();
            this.activity = new Task();
        }
        //change font
        this.event.setFont(Font.font("verdana", FontWeight.BOLD, 15));
        //add the title
        this.getChildren().add(this.event);
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

    //use this constructor if you want to update an event
   public ActivitySelectionView(Activity activity, List<Category> categories, FormDelegate delegate, Stage popupStage) {
        this(activity.getType(), categories, delegate, popupStage);

        //update the pop up to the current values
        this.name.setText(activity.getName());
        this.description.setText(activity.getDescription());
        this.categories.setDefaultStartValue(activity.getCategory());
        this.weekdays.setDefault(activity.getWeekday());

        if (activity.getType().equals(ActivityType.EVENT)) {
            Event event = (Event) activity;
           this.startTime.setDefault(event.getStartTime());
           this.endTime.setDefault(event.getEndTime());
        } else {
            Task task = (Task) activity;
            this.completion.getSelectionModel().select(task.getStatus().getName());
        }
        this.activity = activity;
        //this.event = new Label("Edit");

   }

    private GridPane eventPopUp() {
        //event specific calls
        this.event = new Label("New Event");
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
        this.event = new Label("New Task");
        //completion status combo box
        this.completion = new ComboBox<>();
        this.completion.getItems().addAll(
                CompletionStatus.NOT_STARTED.getName(),
                CompletionStatus.IN_PROGRESS.getName(),
                CompletionStatus.COMPLETED.getName());
        //default value is always to not started when first creating a task
        this.completion.getSelectionModel().select(CompletionStatus.NOT_STARTED.getName());
        GridPane info = new GridPane();
        info.addRow(0, new Label("Task Name: "), this.name);
        info.addRow(1, new Label("Category: "), this.categories);
        info.addRow(2, new Label("Weekday:"), this.weekdays);
        info.addRow(3, new Label("Status:"), this.completion);
        info.addRow(4, new Label("Description: "), this.description);
        return info;
    }

    public Activity submitHandling() {
        //Activity activity;
        if (!validateAnswers()) {
            throw new IllegalArgumentException("You must fill out all the information!");
        }
        else {
            this.activity.setName(this.name.getText());
            this.activity.setDescription(this.description.getText());
            this.activity.setWeekday(this.weekdays.getSelectedWeekDay());
            this.activity.setCategory(categories.getChosenCategory());
            if (this.activityType.equals(ActivityType.EVENT)) {
                Event event = (Event) this.activity;
                event.setStartTime(this.startTime.getTime());
                event.setEndTime(this.endTime.getTime());
            }
            else {
                Task task = (Task) this.activity;
                task.setStatus(CompletionStatus.valueOf(this.completion.getValue().toString().toUpperCase()));
            }
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
