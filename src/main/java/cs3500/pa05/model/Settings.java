package cs3500.pa05.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * represents a singleton class of Settings there should be one and only one instance of Settings
 * for the entire project.
 */
public class Settings {

  //fields
  private String name;
  private String email;
  private int eventMax;
  private int taskMax;
  private final List<Category> categories;
  private String dateString;
  private LocalDate localDate;
  private DayOfWeek startDay;
  public static String SETTING_FILE_DIR = "src/test/resources/settings.bujo";
  private static Settings instance;

  /**
   * Constructor
   */
  private Settings() {
    this.categories = new ArrayList<>();
    this.categories.add(new Category("None", Color.WHITE));
    this.categories.add(new Category("School", Color.WHITE));
    this.categories.add(new Category("Fun", Color.WHITE));
    this.categories.add(new Category("Work", Color.WHITE));
    this.dateString = "6/21/2023";
    this.startDay = DayOfWeek.WEDNESDAY;
  }

  /**
   * getter for the only instance of Settings
   *
   * @return instance of Settings
   */
  public static Settings getInstance() {
    if (instance == null) {
      instance = new Settings();
    }
    return instance;
  }

  /**
   * resetting the singleton instance
   */
  public static void reset() {
    instance = new Settings();
  }

  /**
   * setter for name
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * setter for email
   *
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * setter for maximum event
   *
   * @param eventMax
   */
  public void setEventMax(int eventMax) {
    this.eventMax = eventMax;
  }

  /**
   * setter for maximum task
   *
   * @param taskMax
   */
  public void setTaskMax(int taskMax) {
    this.taskMax = taskMax;
  }

  /**
   * sets the date of the header for the journal
   *
   * @param dateString string
   */
  public void setDateString(String dateString) {this.dateString = dateString;}

  /**
   * Sets the local date of the settings
   *
   * @param localDate given local date to set to
   */
  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  /**
   * Sets the start day of the week to the given DayOfWeek
   *
   * @param startDay given DayOfWeek
   */
  public void setStartDay(DayOfWeek startDay) {
    this.startDay = startDay;
  }

  /**
   * Gets all the days of the week
   *
   * @return list of the days of the week
   */
  public List<DayOfWeek> getDaysOfWeek() {
    List<DayOfWeek> daysOfWeek = new ArrayList<>();

    for (int i = 0; i < DayOfWeek.values().length; i ++) {
      DayOfWeek day = this.startDay.plus(i);
      daysOfWeek.add(day);
    }
    return daysOfWeek;
  }

  /**
   * Gets the date string for the week
   *
   * @return String
   */
  public String getDateString() {return dateString;}

  /**
   * getter for name
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * getter for email
   *
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * getter for max event
   *
   * @return max event
   */
  public int getEventMax() {
    return eventMax;
  }

  /**
   * getter for max task
   *
   * @return max task
   */
  public int getTaskMax() {
    return taskMax;
  }

  /**
   * getter for list of categories
   *
   * @return list of categories
   */
  public List<Category> getCategories() {
    return this.categories;
  }

  /**
   * Gets the local date
   *
   * @return LocateDate - date
   */
  public LocalDate getLocalDate() {
    return this.localDate;
  }
}
