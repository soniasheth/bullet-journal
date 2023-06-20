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

  /**
   * get the hex representation of a color
   * adapted from this <a href="https://stackoverflow.com/questions/
   * 17925318/how-to-get-hex-web-string-from-javafx-colorpicker-color">post</a>
   * @param color color to convert
   * @return hex string for color
   */
  public static String toHex(Color color) {
    return String.format("#%02X%02X%02X",
        (int) (color.getRed() * 255),
        (int) (color.getGreen() * 255),
        (int) (color.getBlue() * 255));
  }
}