package cs3500.pa05.model.activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.Weekday;

import java.time.LocalTime;
import java.util.Queue;

/**
 * represents an event
 */
public class Event extends Activity {
  private final LocalTime startTime;
  private final LocalTime endTime;


  /**
   * default constructor for event
   *
   * @param name        name of the event
   * @param description description of the event
   * @param weekday     weekday of the event
   * @param category    category of the event
   * @param startTime   event start time
   * @param endTime     event end time
   */
  public Event(String name, String description, Weekday weekday, Category category,
               LocalTime startTime, LocalTime endTime) {
    super(name, description, weekday, category, ActivityType.EVENT);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * get the priority of the activity
   *
   * @return priority in int
   */
  @Override
  public int getPriority() {
    return Integer.MAX_VALUE; // event has no priority
  }

  /**
   * add the current activity to given queue
   *
   * @param queue given queue
   */
  @Override
  public void addToTaskQueue(Queue<Activity> queue) {
    // does nothing because an event should not be added to queue
  }

  @Override
  public String toString() {
    String event =
            "Name: " + this.name + "\n"
            + "Category" + this.category.getName() + "\n"
            + "Weekday:" + this.weekday.getRepresentation() + "\n"
            + "Description:" + this.description + "\n"
            + "Start Time" + this.startTime.toString() + "\n"
            + "End Time" + this.endTime.toString();
    return event;
  }
}
