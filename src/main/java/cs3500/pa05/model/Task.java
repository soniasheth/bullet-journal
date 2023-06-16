package cs3500.pa05.model;

import java.util.Queue;

public class Task extends Activity{
  private CompletionStatus status;

  public Task(String name, String description, Weekday weekday, Category category,
              CompletionStatus status) {
    super(name, description, weekday, category);
    this.status = status;
  }

  @Override
  int getPriority() {
    return this.status.getPriority() + this.weekday.ordinal();
  }

  @Override
  void addToTaskQueue(Queue<Activity> ret) {
    ret.add(this);
  }
}
