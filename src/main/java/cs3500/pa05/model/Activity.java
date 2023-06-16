package cs3500.pa05.model;

import java.util.HashMap;
import java.util.Queue;

/**
 * represents an Activity class
 */
public abstract class Activity implements Comparable<Activity>{
  protected String name;
  protected String description;
  protected Weekday weekday;
  protected Category category;

  /**
   * default constructor for activity
   * @param name name of the activity
   * @param description a short description of the activity
   * @param weekday weekday the activity belongs
   * @param category category the activity belongs
   */
  public Activity(String name, String description, Weekday weekday, Category category) {
    this.name = name;
    this.description = description;
    this.weekday = weekday;
    this.category = category;
  }

  abstract int getPriority();

  abstract void addToTaskQueue(Queue<Activity> ret);

  public Weekday getWeekday() {
    return this.weekday;
  }

  @Override
  public int compareTo(Activity that) {
    return this.getPriority() - that.getPriority();
  }
}
