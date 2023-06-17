package cs3500.pa05.controller;

import cs3500.pa05.model.*;
import cs3500.pa05.view.ClickableView;
import cs3500.pa05.view.TableView;
import cs3500.pa05.view.WeekdayView;
import cs3500.pa05.view.TableViewDelegate;
import javafx.scene.control.Button;

/**
 * my controller playground to test stuff
 */
public class JeffreyWangController implements Controller, TableViewDelegate {

  private WeekdayModel model;
  private WeekdayView view;

  public JeffreyWangController(WeekdayView view, Button btn) {
    this.model = new WeekdayModel();
    this.model.addActivity(new Event("field trip", "fun", Weekday.MONDAY, null, null, null));
    this.model.addActivity(new Event("movie night", "fun", Weekday.WEDNESDAY, null, null, null));
    this.model.addActivity(new Task("study for exam", "no", Weekday.THURSDAY, null,
        CompletionStatus.NOT_STARTED));
    this.model.addActivity(new Task("cook", "yeah", Weekday.MONDAY, null,
        CompletionStatus.NOT_STARTED));
    this.view = view;
    this.view.setDelegate(this);
    btn.setOnAction(event -> {
      Weekday newEventDay = Weekday.MONDAY;
      this.model.addActivity(new Task("something new", "new", newEventDay, null, null));
      this.view.reloadAt(newEventDay.ordinal(), this.model.getActivitiesFor(newEventDay).size() - 1);
    });
  }

  /**
   * get how many column should the table display
   *
   * @param tableView reference to delegator
   * @return delegatee return the number of column
   */
  @Override
  public int numberOfColumns(TableView tableView) {
    return Weekday.values().length;
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
    return Weekday.values()[columnIndex].getRepresentation();
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
    return this.model.getActivitiesFor(Weekday.values()[columnIndex]).size();
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
    return this.model.getActivitiesFor(Weekday.values()[columnIndex]).get(rowIndex);
  }
}
