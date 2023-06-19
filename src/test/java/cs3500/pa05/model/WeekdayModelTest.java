package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.List;

import cs3500.pa05.model.Activities.Activity;
import cs3500.pa05.model.Activities.Event;
import cs3500.pa05.model.Activities.Task;
import cs3500.pa05.model.enums.CompletionStatus;
import cs3500.pa05.model.enums.Weekday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents a testing class for WeekdayModel
 */
public class WeekdayModelTest {

  private WeekdayModel model;

  private Task task1;
  private Task task2;
  private Task task3;
  private Task task4;
  private Event event1;
  private Event event2;

  /**
   * helper methods to set up fields before each test
   */
  @BeforeEach
  public void setUp() {
    this.model = new WeekdayModel();
    this.task1 = new Task("shower", "very important", Weekday.FRIDAY,
        new Category("haha", null), CompletionStatus.NOT_STARTED);
    this.task2 = new Task("sleep", "sleeping now", Weekday.FRIDAY,
        new Category("haha", null), CompletionStatus.IN_PROGRESS);
    this.task3 = new Task("essay", "don't wanna write", Weekday.MONDAY,
        new Category("haha", null), CompletionStatus.COMPLETED);
    this.task4 = new Task("prep exam", "no", Weekday.MONDAY,
        new Category("haha", null), CompletionStatus.IN_PROGRESS);
    this.event1 = new Event("date night", "yeah", Weekday.TUESDAY,
        new Category("haha", null), LocalTime.of(20, 0), LocalTime.of(22, 0));
    this.event2 = new Event("IRS audit", "crap", Weekday.SATURDAY,
        new Category("haha", null), LocalTime.of(8, 0), LocalTime.of(8, 30));
    this.model.addActivity(this.task1);
    this.model.addActivity(this.task2);
    this.model.addActivity(this.task3);
    this.model.addActivity(this.task4);
    this.model.addActivity(this.event1);
    this.model.addActivity(this.event2);
  }

  /**
   * testing getTaskQueue
   */
  @Test
  public void testGetTaskQueue() {
    List<Task> items = this.model.getTaskQueue(null);
    assertEquals(items.size(), 4);
    List<Task> ranked = List.of(this.task1, this.task4, this.task2, this.task3);
    for(int i = 0; i < items.size(); i++){
      assertEquals(items.get(i), ranked.get(i));
    }
  }
}