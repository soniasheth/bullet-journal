package cs3500.pa05.controller;

import cs3500.pa05.model.PersistenceManager;
import cs3500.pa05.model.Settings;
import cs3500.pa05.model.WeekdaysModel;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.SettingsView;
import cs3500.pa05.view.WelcomeView;
import cs3500.pa05.view.delegates.FormDelegate;
import cs3500.pa05.view.tables.WeekdaysView;
import java.io.File;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Handles the welcome scene
 */
public class WelcomeController implements Controller, FormDelegate {
    private WeekdaysModel model;
    private WelcomeView welcome;
    private Stage stage;
    private BujoView bujo;
    public WelcomeController(WeekdaysModel model, WelcomeView welcome, Stage stage, BujoView bujo) {
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
            //let the user create a new bullet journal
            Stage settingsPopUP = new Stage();
            VBox settingsView = new SettingsView(Settings.getInstance(), this, settingsPopUP);
            ClosingHandler closing = new ClosingHandler(); //if the user closes the pop up
            settingsPopUP.setOnCloseRequest(closing);
            showPopup(stage, settingsPopUP, settingsView, "New Bullet Journal");
            //only show the bullet journal if the user pressed submit and not the closed button
            if(!closing.getPressed()) {
                //set the bullet journal screen
                Scene customJournal = new Scene(bujo);
                stage.setScene(customJournal);
                stage.show();
            }
        });
    }

    private void handleLoadCurrent() {
        welcome.setOnActionLoad(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open BUJO File");
            ExtensionFilter ef = new ExtensionFilter("BUJO File", "*.bujo");
            fileChooser.getExtensionFilters().add(ef);
            File f = fileChooser.showOpenDialog(stage);
            PersistenceManager.loadDataFrom(f, this.model);
            Scene customJournal = new Scene(bujo);
            stage.setScene(customJournal);
            stage.show();
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

    private class ClosingHandler implements EventHandler {
        boolean pressed = false;
        @Override
        public void handle(Event event) {
            pressed = true;
        }
        public boolean getPressed() {
            return this.pressed;
        }
    }

}
