package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * represents a singleton class of Settings there should be one and only one instance of Settings
 * for the entire project.
 */
public class Settings {

  private String name;
  private String email;
  private int eventMax;
  private int taskMax;
  private final List<Category> categories;

  /**
   * default constructor
   */
  private Settings() {
    this.name = "John Doe";
    this.email = "JohnDoe@fakeEmail.com";
    this.eventMax = 0;
    this.taskMax = 0;
    this.categories = new ArrayList<>();
    this.categories.add(new Category("None", Color.WHITE));
  }

  private static Settings instance;

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
   * @return
   */
  public List<Category> getCategories() {
    return this.categories;
  }
}
