package cs3500.pa05.model;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.CompletionStatus;

/**
 * represents a statistic class for Weekday
 */
public class WeeklyStats {
    private final Map<DayOfWeek, List<Activity>> activities;

    private final List<Category> categories;

    private final List<Task> tasks;

    private final WeekdaysModel weeklyModel;

    //int maxEvents;
    //int maxTasks;
    int totalTasks;

    int tasksCompleted;
    int tasksInProgress;
    int totalEvents;

    Map<Category, Integer> eventCategoryValues;

    Map<Category, Integer> taskCategoryValues;

    public WeeklyStats(WeekdaysModel weeklyModel) {
        this.weeklyModel = weeklyModel;
        this.activities =  weeklyModel.getActivities(null);
        this.categories = Settings.getInstance().getCategories();
        //initialize maxEvents and maxTasks

        this.eventCategoryValues = categoryValues(ActivityType.EVENT);
        this.taskCategoryValues = categoryValues(ActivityType.TASK);


        this.totalTasks = setTotal(taskCategoryValues);
        this.totalEvents = setTotal(eventCategoryValues);

        this.tasks = weeklyModel.getTaskQueue(null);
        this.tasksCompleted = taskNumbers(CompletionStatus.COMPLETED);
        this.tasksInProgress = taskNumbers(CompletionStatus.IN_PROGRESS);



    }

    private int setTotal(Map<Category, Integer> map) {
        int total  = 0;
        for (Integer value: map.values()) {
            total += value;
        }
        return total;
    }

    private Map<Category, Integer> categoryValues(ActivityType type) {
        Map<Category, Integer> ret = new HashMap<>();
        for (Category category : categories) {
            int categoryTotal = 0;
            for (List<Activity> categoryActivities: weeklyModel.getActivities(category).values() ) {
                for (Activity activity : categoryActivities) {
                    if (activity.getType() == type) {
                        categoryTotal++;
                    }
                }
            }
            ret.put(category, categoryTotal);
        }
        return ret;
    }

    private int taskNumbers(CompletionStatus status) {
        int value = 0;
        for (Task task: tasks) {
            if (task.getStatus() == status) {
                value++;
            }
        }
        return value;
    }

    public double getPercentDone() {
        return ((double) tasksCompleted / (double) totalTasks) ;
    }


    public Map<Category, Integer> getEventCategoryValues() {
        return eventCategoryValues;
    }

    public Map<Category, Integer> getTaskCategoryValues() {
        return taskCategoryValues;
    }

    public int getTotalEvents() {
        return totalEvents;
    }

    public int getTotalTasks() {
        return totalTasks;
    }
}
