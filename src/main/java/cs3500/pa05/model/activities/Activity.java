package cs3500.pa05.model.activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.Weekday;

/**
 * represents an Activity class
 */
public abstract class Activity implements Comparable<Activity> {
  protected String name;
  protected String description;
  protected Weekday weekday;
  protected Category category;

  /**
   * default constructor for activity
   *
   * @param name        name of the activity
   * @param description a short description of the activity
   * @param weekday     weekday the activity belongs
   * @param category    category the activity belongs
   */
  public Activity(String name, String description, Weekday weekday, Category category) {
    this.name = name;
    this.description = description;
    this.weekday = weekday;
    this.category = category;
  }

  public Activity() {
    this.name = null;
    this.description = null;
    this.weekday = null;
    this.category = null;
  }

  /**
   * get the priority of the activity
   *
   * @return priority in int
   */
  public abstract int getPriority();

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
  abstract public ActivityType getType();

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

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription (String description) {
    this.description = description;
  }

  public void setWeekday (Weekday weekday) {
    this.weekday = weekday;
  }

  public void setCategory (Category cat) {
    this.category = cat;
  }
}
