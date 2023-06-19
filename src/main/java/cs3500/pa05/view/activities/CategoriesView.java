package cs3500.pa05.view.activities;

import cs3500.pa05.model.Category;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CategoriesView extends ComboBox {
  private List<Category> existingCategories;

  //make sure that the list of categories had none at the end
  public CategoriesView(List<Category> categories) {
    this.existingCategories = categories;
    for (Category category : categories) {
      this.getItems().add(category.getName());
    }
    this.setEditable(true);
  }

  public Category getChosenCategory() {
    if (!validateAnswer()) {
      return null;
    }
    else {
      String category = this.getValue().toString();
      Category userChoice = null;
      boolean found = false;
      //as long at the catergory is not null
      // see if its an existing category
      for (Category existingCategory : existingCategories) {
        if (category.equals(existingCategory)) {
          userChoice = new Category(existingCategory.getName(), existingCategory.getColor());
          found = true;
        }
      }
      // not an existing cat-> create one
      //default color is red for now
      if (!found) {
        userChoice = new Category(category, Color.RED);
      }
      return userChoice;
    }
  }

  private boolean validateAnswer() {
    return this.getValue() != null;
  }

  public void setDefaultStartValue(Category name) {
    this.getSelectionModel().select(name.getName());
  }

}
