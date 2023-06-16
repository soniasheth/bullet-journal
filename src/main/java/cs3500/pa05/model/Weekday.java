package cs3500.pa05.model;

/**
 * represents a enumeration of Weekday
 */
public enum Weekday {
  MONDAY("Monday"),
  TUESDAY("Tuesday"),
  WEDNESDAY("Wednesday"),
  THURSDAY("Thursday"),
  FRIDAY("Friday"),
  SATURDAY("Saturday"),
  SUNDAY("Sunday");

  private final String representation;

  private Weekday(String representation){
    this.representation = representation;
  }

  /**
   * get the String representation of the enum
   * @return String representation
   */
  public String getRepresentation() {
    return this.representation;
  }
}
