package cs3500.pa05.model;

import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.DayOfWeek;


/**
 * represents a weekday model class for bullet journal
 */
public class WeekdaysModel implements Model {

  private final Map<DayOfWeek, List<Activity>> activities;

  /**
   * default constructor that initialize an empty map of activities
   */
  public WeekdaysModel() {
    this.activities = new HashMap<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      this.activities.put(day, new ArrayList<>());
    }
  }

  /**
   * add an activity to a specific week
   *
   * @param activity activity to add
   */
  public void addActivity(Activity activity) {
    this.removeIfExist(activity);
    this.activities.get(activity.getWeekday()).add(activity);
  }

  public void removeActivity(Activity activity) {
    DayOfWeek day = activity.getWeekday();
    Activity toRemove = null;
    List<Activity> dayActivities = activities.get(day);
    for (Activity dayActivity : dayActivities) {
      if (dayActivity.equals(activity)) {
        toRemove = dayActivity;
      }
    }

    dayActivities.remove(toRemove);
  }
  /**
   * private method to remove an activity if exist
   *
   * @param activity activity instance
   */
  private void removeIfExist(Activity activity) {
    for (DayOfWeek weekday : DayOfWeek.values()) {
      List<Activity> items = this.activities.get(weekday);
      for (int i = 0; i < items.size(); i++) {
        if (activity == items.get(i)) {
          items.remove(activity);
          return;
        }
      }
    }
  }

  /**
   * get all activities of a specific category
   *
   * @param category category to filter, or null
   * @return activities
   */
  public Map<DayOfWeek, List<Activity>> getActivities(Category category) {
    if (category == null) {
      return this.activities;
    }

    Map<DayOfWeek, List<Activity>> ret = new HashMap<>();
    for (DayOfWeek weekday : DayOfWeek.values()) {
      ret.put(weekday, this.activities.get(weekday).stream()
          .filter(element -> element.getCategory().equals(category)).toList());
    }
    return ret;
  }

  /**
   * get a queue of all tasks of a specific category, ranked by priority
   *
   * @param category category to filter, or null
   * @return a queue of tasks
   */
  public List<Task> getTaskQueue(Category category) {
    List<Task> ret = new ArrayList<>();
    for (List<Activity> dayActivities : this.activities.values()) {
      for (Activity activity : dayActivities) {
        if (activity.getType() == ActivityType.TASK) {
          ret.add(
              (Task) activity); //casting because we are checking to ensure it is a task beforehand
        }
      }
    }
    Collections.sort(ret);
    if (category == null) {
      return ret;
    }
    return ret.stream().filter(element -> element.getCategory().equals(category)).toList();
  }

  /**
   * iterate through the current activities and see if user exceed any limit
   *
   * @return true if user exceeds the limit
   */
  public boolean shouldDisplayCommitmentWarning() {
    int maxTask = Settings.getInstance().getTaskMax();
    int maxEvent = Settings.getInstance().getEventMax();
    int curTask = 0;
    int curEvent = 0;
    for (DayOfWeek weekday : DayOfWeek.values()) {
      for (Activity activity : this.activities.get(weekday)) {
        if (activity.getType() == ActivityType.EVENT) {
          curEvent += 1;
        } else {
          curTask += 1;
        }
        if (curTask > maxTask || curEvent > maxEvent) {
          return true;
        }
      }
    }
    return false;
  }
}
