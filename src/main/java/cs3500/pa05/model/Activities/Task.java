package cs3500.pa05.model.Activities;

import cs3500.pa05.model.Category;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.model.enums.Weekday;

import java.util.Queue;

/**
 * represents a Task class
 */
public class Task extends Activity {
  private final CompletionStatus status;

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
    super(name, description, weekday, category, ActivityType.TASK);
    this.status = status;
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

  /**
   * add the current activity to given queue
   *
   * @param queue given queue
   */
  @Override
  public void addToTaskQueue(Queue<Activity> queue) {
    queue.add(this);
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
}
