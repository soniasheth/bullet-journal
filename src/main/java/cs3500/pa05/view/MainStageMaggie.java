package cs3500.pa05.view;


import cs3500.pa05.model.Settings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainStageMaggie extends Application {

  @Override

  public void start(Stage primaryStage) throws Exception {

    Button settingsButton = new Button("settings");
    settingsButton.setOnAction(e -> settingsPopUp(primaryStage));
    VBox root = new VBox();
    root.getChildren().add(settingsButton);
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  private void settingsPopUp(Stage primaryStage) {
    Stage settingsPopup = new Stage();
    settingsPopup.initOwner(primaryStage);
    settingsPopup.initModality(Modality.APPLICATION_MODAL);
    settingsPopup.setTitle("Settings");
    Settings setting = new Settings();
    SettingsView v = new SettingsView(setting, true, settingsPopup);
    Scene popupScene = new Scene(v);
    settingsPopup.setScene(popupScene);
    settingsPopup.showAndWait();
  }

}