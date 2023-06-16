package cs3500.pa05.view;

import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Weekday;
import javafx.scene.control.Button;

/**
 * represent a delegate interface for WeekdayView
 * the delegator ask for data of each activity
 * the delegatee provides data and handle events
 */
public interface WeekdayViewDelegate {
  /**
   * get how many activities exist on a given weekday
   * @param weekday weekday
   * @return delegatee return the number of activities on that weekday
   */
  default int numberOfActivitiesOnDay(Weekday weekday){
    return 0;
  }

  /**
   * get the data of nth event/task on a specific day
   * @param weekday day of the activity
   * @param index nth activity on that day
   * @return an instance of event/task
   */
  Activity dataForActivityOn(Weekday weekday, int index);

  default void setEventHandlerFor(Button button, Weekday weekday, int index){
    // by default, there is no event handler, so no event handler is set
  }
}
