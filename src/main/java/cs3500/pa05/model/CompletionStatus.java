package cs3500.pa05.model;

/**
 * represents an enumeration of task completion status
 */
public enum CompletionStatus {
  NOT_STARTED(0),
  IN_PROGRESS(10),
  COMPLETED(20);

  private final int priority;

  /**
   * default constructor for completion status
   *
   * @param priority the priority in integer
   */
  private CompletionStatus(int priority) {
    this.priority = priority;
  }

  /**
   * getter for priority
   *
   * @return priority
   */
  public int getPriority() {
    return this.priority;
  }
}
