package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.model.Settings;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.SettingsView;
import cs3500.pa05.view.WelcomeView;
import cs3500.pa05.view.delegates.FormDelegate;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Handles the welcome scene
 */
public class WelcomeController implements Controller, FormDelegate {
    private Model model;
    private WelcomeView welcome;
    private Stage stage;
    private BujoView bujo;
    public WelcomeController(Model model, WelcomeView welcome, Stage stage, BujoView bujo) {
        this.model = model;
        this.welcome = welcome;
        this.bujo = bujo;
        this.stage = stage;
        handleCreateNewJournal();
        handleLoadCurrent();
    }

    //when create new is pressed, prompt user to enter settings and then it will switch the scene to a the bullet journal
    private void handleCreateNewJournal() {
        welcome.setOnActionCreate(event -> {
            //let the user customize settings
            VBox settingsView = new SettingsView(Settings.getInstance(), this, stage);
            stage.setOnCloseRequest(event1 -> {
                System.out.print("closed");
            });
            showPopup(stage, new Stage(), settingsView, "New Bullet Journal");
            Scene customJournal = new Scene(bujo);
            stage.setScene(customJournal);
            stage.show();
        });
    }

    private void handleLoadCurrent() {
        welcome.setOnActionLoad(event -> {
            //TODO
        });
    }

    private void showPopup(Stage ownerStage, Stage popupStage, Parent popUp, String title) {
        popupStage.initOwner(ownerStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(title);
        Scene popupScene = new Scene(popUp);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }

    @Override
    public void submit(FormView formView, Object object) {
        //not really needed
    }
}
