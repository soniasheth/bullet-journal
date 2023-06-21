package cs3500.pa05;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

public class Utils {

  public static void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.show();
  }


  public static boolean isValidNumber(String input) {
    try {
      int num = Integer.parseInt(input);
      return num >= 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isValidEmail(String input) {
    return input.endsWith(".com") && input.contains("@");
  }
}