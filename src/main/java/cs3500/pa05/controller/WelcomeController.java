package cs3500.pa05.controller;

import cs3500.pa05.model.Model;
import cs3500.pa05.model.Settings;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.SettingsView;
import cs3500.pa05.view.WelcomeView;
import cs3500.pa05.view.delegates.FormDelegate;
import cs3500.pa05.view.delegates.TableViewDelegate;
import cs3500.pa05.view.tables.TableView;
import cs3500.pa05.view.tables.WeekdaysView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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


    private TableView weekdaysView;

    HBox weekOfLabel;

  
    /**
     * Constructor
     *
     * @param model model for the program
     * @param welcome the welcome view
     * @param stage stage for the program
     * @param bujo the view for the bullet journal
     */
    public WelcomeController(Model model, WelcomeView welcome, Stage stage, BujoView bujo, TableView weekdaysView, HBox weekOfLabel) {

        this.model = model;
        this.welcome = welcome;
        this.bujo = bujo;
        this.stage = stage;

        this.weekdaysView = weekdaysView;
        this.weekOfLabel = weekOfLabel;


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
            VBox settingsView = new SettingsView(Settings.getInstance(), this, settingsPopUP, true);
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
        Text weekOf = new Text("Week of " + Settings.getInstance().getDateString());

        weekOf.setFont(Font.font("Bradley Hand", FontWeight.EXTRA_BOLD, 35));
        weekOf.setFill(Color.valueOf("228B22"));

        this.weekOfLabel.getChildren().add(weekOf);

        weekdaysView.reloadAll();
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
