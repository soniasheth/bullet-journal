package cs3500.pa05.view;

import cs3500.pa05.model.Activity;

/**
 * represent a delegate interface for WeekdayView
 * the delegator ask for data of each activity
 * the delegatee provides data and handle events
 */
public interface TableViewDelegate {
  /**
   * get how many column should the table display
   * @param tableView reference to delegator
   * @return delegatee return the number of column
   */
  default int numberOfColumns(TableView tableView){
    return 0;
  }

  /**
   * get the title for each column
   * @param tableView reference to delegator
   * @param columnIndex columnIndex of the table
   * @return title in String
   */
  String titleForColumn(TableView tableView, int columnIndex);

  /**
   * get number of row for a specific column
   * @param tableView reference to delegator
   * @param columnIndex column index
   * @return number of element in this column
   */
  int numberOfRowFor(TableView tableView, int columnIndex);

  /**
   * get the data of nth event/task on a specific day
   * @param tableView reference to delegator
   * @param columnIndex columnIndex of the table
   * @param rowIndex rowIndex of the table
   * @return an instance of event/task
   */
  Activity dataForActivityOn(TableView tableView, int columnIndex, int rowIndex);
}
