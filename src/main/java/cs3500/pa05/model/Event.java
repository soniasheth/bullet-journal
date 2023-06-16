package cs3500.pa05.model;

import java.time.LocalTime;
import java.util.Queue;

public class Event extends Activity{
  private LocalTime startTime;
  private LocalTime endTime;

  public Event(String name, String description, Weekday weekday, Category category,
               LocalTime startTime, LocalTime endTime) {
    super(name, description, weekday, category);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  int getPriority() {
    return Integer.MAX_VALUE; // event has no priority
  }

  @Override
  void addToTaskQueue(Queue<Activity> ret) {
    // does nothing because an event should not be added to queue
  }
}
