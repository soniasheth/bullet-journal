package cs3500.pa05.view;

import cs3500.pa05.model.Category;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CategoriesView extends ComboBox {

  private TextField otherOption;
  private List<Category> existingCategories;

  public CategoriesView(List<Category> categories) {
    this.existingCategories = categories;
    for(int i = 0; i < categories.size(); i++) {
      this.getItems().add(categories.get(i).getName());
    }
  }


}
