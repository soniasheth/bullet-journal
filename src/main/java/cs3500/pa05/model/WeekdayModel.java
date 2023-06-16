package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import javafx.scene.paint.Color;

/**
 * represents a weekday model class for bullet journal
 */
public class WeekdayModel implements Model {
  private final Map<Weekday, List<Activity>> activities;
  private final List<Category> categories;

  /**
   * default constructor that initialize an empty map of activities
   */
  public WeekdayModel() {
    this.activities = new HashMap<>();
    for (Weekday day : Weekday.values()) {
      this.activities.put(day, new ArrayList<>());
    }

    this.categories = new ArrayList<>();
    this.categories.add(new Category("N/A", Color.WHITE));
  }

  /**
   * add an activity to a specific week
   *
   * @param weekday  weekday the activity belongs
   * @param activity activity to add
   */
  public void addActivity(Activity activity) {
    this.activities.get(activity.getWeekday()).add(activity);
  }

  /**
   * get a queue of all tasks in activities, ranked by priority
   *
   * @return a queue of tasks
   */
  public Queue<Activity> getTaskQueue() {
    Queue<Activity> ret = new PriorityQueue<>();
    for (List<Activity> dayActivities : this.activities.values()) {
      for (Activity activity : dayActivities) {
        activity.addToTaskQueue(ret);
      }
    }
    return ret;
  }

  /**
   * calculate and return stats of current activities
   *
   * @return a WeekdayStat instance
   */
  public WeekdayStat getStats() {
    throw new UnsupportedOperationException("not yet implemented");
  }

  /**
   * get all categories including default ones and user defined ones
   * @return a list of category
   */
  public List<Category> getCategories(){
    return this.categories;
  }
}
