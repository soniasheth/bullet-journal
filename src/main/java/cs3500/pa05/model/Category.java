package cs3500.pa05.model;

import java.util.HashMap;
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

  public String getName() {
    return this.name;
  }

  public Color getColor() {
    return this.color;
  }

  /**
   * comparing equality with another object
   * @param other other object for comparison
   * @return true if the other object is equal to this
   */
  @Override
  public boolean equals(Object other) {
    if(other instanceof Category that){
      return that.name.equals(this.name);
    }
    return false;
  }

  /**
   * get the hashCode of this
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return this.name.hashCode();
  }
}
