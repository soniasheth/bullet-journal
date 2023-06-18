package cs3500.pa05.model.Activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.Weekday;
import java.util.Queue;

/**
 * represents an Activity class
 */
public abstract class Activity implements Comparable<Activity> {
  protected String name;
  protected String description;
  protected Weekday weekday;
  protected Category category;
  protected ActivityType type;

  /**
   * default constructor for activity
   *
   * @param name        name of the activity
   * @param description a short description of the activity
   * @param weekday     weekday the activity belongs
   * @param category    category the activity belongs
   */
  public Activity(String name, String description, Weekday weekday, Category category, ActivityType type) {
    this.name = name;
    this.description = description;
    this.weekday = weekday;
    this.category = category;
    this.type = type;
  }

  /**
   * get the priority of the activity
   *
   * @return priority in int
   */
  public abstract int getPriority();

  /**
   * add the current activity to given queue
   *
   * @param queue given queue
   */
  public abstract void addToTaskQueue(Queue<Activity> queue);

  /**
   * getter for weekday
   *
   * @return weekday
   */
  public Weekday getWeekday() {
    return this.weekday;
  }

  /**
   * getter for name
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * getter for description
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * getter for category
   * @return category
   */
  public Category getCategory() {
    return category;
  }

  /**
   * getter for type
   * @return type
   */
  public ActivityType getType() {
    return type;
  }

  /**
   * compare the current activity to given activity based on priority
   *
   * @param that the object to be compared.
   * @return difference
   */
  @Override
  public int compareTo(Activity that) {
    return this.getPriority() - that.getPriority();
  }
}