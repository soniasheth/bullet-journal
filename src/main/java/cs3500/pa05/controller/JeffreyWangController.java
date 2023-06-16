package cs3500.pa05.controller;

import cs3500.pa05.model.*;
import cs3500.pa05.view.TableView;
import cs3500.pa05.view.WeekdayView;
import cs3500.pa05.view.WeekdayViewDelegate;
import javafx.scene.control.Button;

/**
 * my controller playground to test stuff
 */
public class JeffreyWangController implements Controller, WeekdayViewDelegate {

    private WeekdayModel model;
    private WeekdayView view;

    public JeffreyWangController(WeekdayView view) {
        this.model = new WeekdayModel();
        this.model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, null, null, null));
        this.model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, null, null, null));
        this.model.addActivity(new Task("laundry", "f", Weekday.THURSDAY, null, CompletionStatus.NOT_STARTED));
        this.model.addActivity(new Task("cook", "f", Weekday.MONDAY, null, CompletionStatus.NOT_STARTED));
        this.view = view;
        this.view.setDelegate(this);
    }

    @Override
    public int numberOfActivitiesOnDay(Weekday weekday) {
        return this.model.getActivitiesFor(weekday).size();
    }

    @Override
    public Activity dataForActivityOn(Weekday weekday, int index) {
        return this.model.getActivitiesFor(weekday).get(index);
    }

    @Override
    public void setEventHandlerFor(Button button, Weekday weekday, int index) {
        button.setOnAction(event -> {
            this.model.addActivity(new Task("prev: " + index, "", weekday, null, null));
            this.view.reloadAt(weekday.ordinal());
        });
    }
}
