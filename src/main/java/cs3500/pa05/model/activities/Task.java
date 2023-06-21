package cs3500.pa05.model.activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.model.enums.Weekday;

/**
 * represents a Task class
 */
public class Task extends Activity {
  private CompletionStatus status;

  /**
   * default constructor for a task
   *
   * @param name        name of the task
   * @param description description of the task
   * @param weekday     weekday of the task
   * @param category    category of the task
   * @param status      task status
   */
  public Task(String name, String description, Weekday weekday, Category category,
              CompletionStatus status) {
    super(name, description, weekday, category);
    this.status = status;
  }

  public Task() {
    this.status = null;
  }

  /**
   * get the priority of the activity
   *
   * @return priority in int
   */
  @Override
  public int getPriority() {
    return this.status.getPriority() + this.weekday.ordinal();
  }

  public String toString() {
    return "Name: " + this.name + "\n"
            + "Category" + this.category.getName() + "\n"
            + "Weekday:" + this.weekday.getRepresentation() + "\n"
            + "Description:" + this.description + "\n";
  }

  public CompletionStatus getStatus() {
    return status;
  }

  public void setStatus(CompletionStatus status) {
    this.status = status;
  }

  /**
   * getter for type
   *
   * @return type
   */
  @Override
  public ActivityType getType() {
    return ActivityType.TASK;
  }


}
