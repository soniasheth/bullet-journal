package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;

public class Settings {

  private String name;
  private String email;
  private int eventMax;
  private int taskMax;
  private List<Category> categories;

  private int week;

  private Settings() {
    this.name = "John Doe";
    this.email = "JohnDoe@fakeEmail.com";
    this.eventMax = 0;
    this.taskMax = 0;
    this.categories = new ArrayList<>();
    this.categories.add(new Category("None", Color.WHITE));

   this.week = 0;
  }

  private static Settings instance;

  public static Settings getInstance() {
    if (instance == null) {
      instance = new Settings();
    }
    return instance;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEventMax(int eventMax) {
    this.eventMax = eventMax;
  }

  public void setTaskMax(int taskMax) {
    this.taskMax = taskMax;
  }

  public void setWeek(int week) {this.week = week;}

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int getEventMax() {
    return eventMax;
  }

  public int getTaskMax() {
    return taskMax;
  }

  public int getWeek() {return week;}

  public List<Category> getCategories(){
    return this.categories;
  }
}
