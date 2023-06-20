package cs3500.pa05.view;

import cs3500.pa05.model.Settings;
import cs3500.pa05.view.activities.ActivitiesButtons;
import cs3500.pa05.view.tables.TaskQueueView;
import cs3500.pa05.view.tables.WeekdaysView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BujoView extends BorderPane {
    private final int height = 1000;
    private final int width = 2000;
    ActivitiesButtons activities;
    Button settings;
    Button save;
    WeekdaysView weekdaysView;
    TaskQueueView taskQueueView;

    Text weekOf;

    public BujoView(ActivitiesButtons activities, Button settings, Button save, WeekdaysView weekdaysView,
                    TaskQueueView taskQueueView, Button eventStats, Button tasksStats) {
        this.setPadding(new Insets(20, 20, 20, 20));

        //init
        this.activities = activities;
        this.settings = settings;
        this.save = save;
        this.weekdaysView = weekdaysView;
        this.taskQueueView = taskQueueView;

        //Create the Week of Label
        HBox weekOfLabel = new HBox();
        this.weekOf = new Text("Week of " + Settings.getInstance().getWeek());

        weekOf.setFont(Font.font("Bradley Hand", FontWeight.EXTRA_BOLD, 35));
        weekOfLabel.getChildren().add(weekOf);

        //set the top of the border pane with the Week of label and the settings / save button
        HBox settingsAndSaveButtons = new HBox(this.settings, this.save);
        settingsAndSaveButtons.setSpacing(15);
        HBox top = new HBox(weekOfLabel, settingsAndSaveButtons);
        top.setSpacing((double) width / 2 - 25);
        this.setTop(top);
        setMargin(top, new Insets(20, 20, 20, 0));

        //create the stats vbox
        VBox stats = new VBox(eventStats, tasksStats);
        stats.setAlignment(Pos.CENTER);
        stats.setSpacing(20);

        //set the left
        VBox leftPane = new VBox(this.activities, this.taskQueueView, stats);
        leftPane.setSpacing(20);
        this.setLeft(leftPane);
        setMargin(leftPane, new Insets(0, 25, 0, 0));

        //set the center
        this.setCenter(weekdaysView);
        setAlignment(this.weekdaysView, Pos.TOP_CENTER);
    }


}
