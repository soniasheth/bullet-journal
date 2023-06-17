package cs3500.pa05.controller;

import cs3500.pa05.model.*;
import cs3500.pa05.view.TableView;
import cs3500.pa05.view.TableViewDelegate;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * my controller playground to test stuff
 */
public class JeffreyWangController implements Controller, TableViewDelegate {

  private WeekdayModel model;
  private List<Activity> taskQueue;
  private TableView weekendView;
  private TableView taskQueueView;

  public JeffreyWangController(TableView weekendView, TableView taskQueueView, Button btn) {
    this.model = new WeekdayModel();
    this.model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, null, null, null));
    this.model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, null, null, null));
    this.model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, null,
        CompletionStatus.NOT_STARTED));
    this.model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, null,
        CompletionStatus.NOT_STARTED));
    this.taskQueue = new ArrayList<>(this.model.getTaskQueue());
    this.weekendView = weekendView;
    this.weekendView.setDelegate(this);
    this.taskQueueView = taskQueueView;
    this.taskQueueView.setDelegate(this);
    btn.setOnAction(event -> {
      Weekday newEventDay = Weekday.MONDAY;
      this.model.addActivity(new Task("something new", "new", newEventDay, null,
          CompletionStatus.NOT_STARTED));
      this.weekendView.reloadAt(newEventDay.ordinal(),
          this.model.getActivitiesFor(newEventDay).size() - 1);
      this.taskQueue = new ArrayList<>(this.model.getTaskQueue());
      this.taskQueueView.reloadAll();
    });
  }

  /**
   * get the title for each column
   *
   * @param tableView   reference to delegator
   * @param columnIndex columnIndex of the table
   * @return title in String
   */
  @Override
  public String titleForColumn(TableView tableView, int columnIndex) {
    if(tableView == this.weekendView){
      return Weekday.values()[columnIndex].getRepresentation();
    }
    return String.format("your queue: (%d)", this.taskQueue.size());
  }

  /**
   * get number of row for a specific column
   *
   * @param tableView   reference to delegator
   * @param columnIndex column index
   * @return number of element in this column
   */
  @Override
  public int numberOfRowFor(TableView tableView, int columnIndex) {
    if (tableView == this.weekendView) {
      return this.model.getActivitiesFor(Weekday.values()[columnIndex]).size();
    }
    return this.taskQueue.size();
  }

  /**
   * get the data of nth event/task on a specific day
   *
   * @param tableView   reference to delegator
   * @param columnIndex columnIndex of the table
   * @param rowIndex    rowIndex of the table
   * @return an instance of event/task
   */
  @Override
  public Activity dataForActivityOn(TableView tableView, int columnIndex, int rowIndex) {
    if (tableView == this.weekendView) {
      return this.model.getActivitiesFor(Weekday.values()[columnIndex]).get(rowIndex);
    }
    return this.taskQueue.get(rowIndex);
  }
}
