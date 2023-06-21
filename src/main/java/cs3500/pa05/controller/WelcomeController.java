package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.model.Settings;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.SettingsView;
import cs3500.pa05.view.WelcomeView;
import cs3500.pa05.view.delegates.FormDelegate;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller that handles the Welcome Scene
 */
public class WelcomeController implements Controller, FormDelegate {
    //fields
    private Model model;
    private WelcomeView welcome;
    private Stage stage;
    private BujoView bujo;

    /**
     * Constructor
     *
     * @param model model for the program
     * @param welcome the welcome view
     * @param stage stage for the program
     * @param bujo the view for the bullet journal
     */
    public WelcomeController(Model model, WelcomeView welcome, Stage stage, BujoView bujo) {
        this.model = model;
        this.welcome = welcome;
        this.bujo = bujo;
        this.stage = stage;

        //handles the set up
        handleCreateNewJournal();
        handleLoadCurrent();
    }

    /**
     * Sets up the event handler for the Create New journal button
     */
    private void handleCreateNewJournal() {
        //when create new is pressed, prompt user to enter settings and then it will switch the scene to a new journal
        welcome.setOnActionCreate(event -> {
            //let the user create a new bullet journal
            Stage settingsPopUP = new Stage();
            VBox settingsView = new SettingsView(Settings.getInstance(), this, settingsPopUP);
            ClosingHandler closing = new ClosingHandler(); //if the user closes the pop up - handles it
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

    /**
     * Sets up the event handler for the Upload button
     */
    private void handleLoadCurrent() {
        //when pressed, user can choose a file and that journal will be parsed
        welcome.setOnActionLoad(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open BUJO File");
            fileChooser.showOpenDialog(stage);
        });
    }

    @Override
    public void submit(FormView formView, Object object) {
        //not really needed
    }

    /**
     * Class that represents an event handler for when closing a button
     */
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
