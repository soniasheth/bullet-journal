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
    for(int i = 0; i < categories.size(); i++) {
      this.getItems().add(categories.get(i).getName());
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
       for (int i = 0; i < existingCategories.size(); i++) {
         if (category.equals(existingCategories.get(i))) {
           userChoice = new Category(existingCategories.get(i).getName(), existingCategories.get(i).getColor());
           found = true;
         }
       }
      // not an existing cat-> create one
      //default color is red for now
      if (found == false) {
        userChoice = new Category(category, Color.RED);
      }
      return userChoice;
    }
  }

  private boolean validateAnswer() {
    if (this.getValue() == null) {
      return false;
    }
    else {
      return true;
    }
  }

  public void setDefaultStartValue(Category name) {
    this.getSelectionModel().select(name.getName());
  }

}
