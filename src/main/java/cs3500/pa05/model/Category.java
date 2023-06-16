package cs3500.pa05.model;

import javafx.scene.paint.Color;

/**
 * represents a Category class
 */
public class Category {
  String name;
  Color color;

  /**
   * default constructor for category
   *
   * @param name  name of the category
   * @param color color associated with the category
   */
  public Category(String name, Color color) {
    this.name = name;
    this.color = color;
  }
}
