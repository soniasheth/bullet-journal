package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeekdayModelTest {

  private WeekdayModel model;

  private Activity task1;
  private Activity task2;
  private Activity task3;
  private Activity task4;
  private Activity event1;
  private Activity event2;

  @BeforeEach
  public void setUp() {
    this.model = new WeekdayModel();
    this.task1 = new Task("shower", "very important", Weekday.FRIDAY,
        this.model.getCategories().get(0), CompletionStatus.NOT_STARTED);
    this.task2 = new Task("sleep", "sleeping now", Weekday.FRIDAY,
        this.model.getCategories().get(0), CompletionStatus.IN_PROGRESS);
    this.task3 = new Task("essay", "don't wanna write", Weekday.MONDAY,
        this.model.getCategories().get(0), CompletionStatus.COMPLETED);
    this.task4 = new Task("prep exam", "no", Weekday.MONDAY,
        this.model.getCategories().get(0), CompletionStatus.IN_PROGRESS);
    this.event1 = new Event("date night", "yeah", Weekday.TUESDAY,
        this.model.getCategories().get(0), LocalTime.of(20, 0), LocalTime.of(22, 0));
    this.event2 = new Event("IRS audit", "crap", Weekday.SATURDAY,
        this.model.getCategories().get(0), LocalTime.of(8, 0), LocalTime.of(8, 30));
    this.model.addActivity(this.task1);
    this.model.addActivity(this.task2);
    this.model.addActivity(this.task3);
    this.model.addActivity(this.task4);
    this.model.addActivity(this.event1);
    this.model.addActivity(this.event2);
  }

  @Test
  public void testGetTaskQueue() {
    Queue<Activity> queue = this.model.getTaskQueue();
    assertEquals(queue.size(), 4);
    List<Activity> ranked = List.of(this.task1, this.task4, this.task2, this.task3);
    for(Activity activity: ranked){
      assertEquals(activity, queue.remove());
    }
    assertTrue(queue.isEmpty());
  }
}