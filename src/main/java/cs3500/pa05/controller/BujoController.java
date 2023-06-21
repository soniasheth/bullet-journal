package cs3500.pa05.controller;

import cs3500.pa05.Utils;
import cs3500.pa05.model.*;
import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.FormView;
import cs3500.pa05.view.MiniViewer;
import cs3500.pa05.view.SettingsView;
import cs3500.pa05.view.WeeklyStatsView;
import cs3500.pa05.view.WelcomeView;
import cs3500.pa05.view.activities.ActivitySelectionView;
import cs3500.pa05.view.activities.ActivitiesButtons;
import cs3500.pa05.view.delegates.FormDelegate;
import cs3500.pa05.view.tables.TableView;
import cs3500.pa05.view.delegates.TableViewDelegate;
import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.List;

/**
 * Represents the controller for the Bullet Journal Scene
 */
public class BujoController implements Controller, TableViewDelegate, FormDelegate {
  //fields
  private final Stage mainStage;
  private final WeekdaysModel model;
  private final Map<Weekday, List<Activity>> activities;
  private List<Task> taskQueue;
  private final Category filterCategory = null;
  private final TableView weekendView;
  private final TableView taskQueueView;

  /**
   * Constructor
   *
   * @param mainStage main stage of the program
   * @param model model holding data
   * @param weekendView the view of all the weekdays
   * @param taskQueueView the view of the task view
   * @param activities the buttons for event and task
   * @param settings settings button
   * @param eventStats button for event stats
   * @param taskStats button for taskStats
   * @param save save button
   */
  public BujoController(Stage mainStage, WeekdaysModel model, TableView weekendView,
      TableView taskQueueView, ActivitiesButtons activities, Button settings, Button eventStats,
      Button taskStats, Button save) {

    //assigne fields
    this.mainStage = mainStage;
    this.model = model;
    this.activities = this.model.getActivities(this.filterCategory);
    this.taskQueue = this.model.getTaskQueue(this.filterCategory);
    this.weekendView = weekendView;
    this.weekendView.setDelegate(this);
    this.taskQueueView = taskQueueView;
    this.taskQueueView.setDelegate(this);

    //call event handlers
    handleActivities(activities);
    handleSettings(settings);
    handleEventStats(eventStats);
    handleTaskStats(taskStats);
    handleSave(save);
  }

  /**
   * Sets up the event handlers for the event and task buttons
   *
   * @param activities event and task buttons wrapped in a singular class
   */
  public void handleActivities(ActivitiesButtons activities) {
    //handle pop up for pushing the buttons creating new activities
    //event button event handling
    activities.setOnActionEventButton(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s, new ActivitySelectionView(ActivityType.EVENT,
          Settings.getInstance().getCategories(), this, s), "New Event");
    });
    //task button event handling
    activities.setOnActionTaskButton(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s, new ActivitySelectionView(ActivityType.TASK,
          Settings.getInstance().getCategories(), this, s), "New Task");
    });
  }

  /**
   * Sets up the event handler for the event stats button
   *
   * @param b event stats button
   */
  public void handleEventStats(Button b) {
    b.setOnAction(event -> {
      Stage s = new Stage();
      WeeklyStat stats = new WeeklyStat(model);
      this.showPopup(this.mainStage, s, new WeeklyStatsView(stats, ActivityType.EVENT), "Event Stats");
    });
  }

  /**
   * Sets up the event handler for the task stats button
   *
   * @param b task stats button
   */
  public void handleTaskStats(Button b) {
    b.setOnAction(event -> {
      Stage s = new Stage();
      WeeklyStat stats = new WeeklyStat(model);
      this.showPopup(this.mainStage, s, new WeeklyStatsView(stats, ActivityType.TASK), "Task Stats");
    });
  }

  /**
   * Sets up the event handler for the settings button
   *
   * @param settings settings button
   */
  public void handleSettings(Button settings) {
    //handles pop up when pushing the settings button
    settings.setOnAction(event -> {
      Stage popup = new Stage();
      VBox settingsView = new SettingsView(Settings.getInstance(),  this, popup);
      this.showPopup(this.mainStage, popup, settingsView, "Settings");
    });
  }

  /**
   * Sets up the event handler for the save button
   *
   * @param save save button
   */
  public void handleSave(Button save){
    save.setOnAction(event -> {
      PersistenceManager.saveSettingsTo(Settings.SETTING_FILE_DIR);
    });
  }

  /**
   * get the title for each column
   *
   * @param tableView   reference to delegator
   * @param columnIndex columnIndex of the table
   * @return title in String
   */
  @Override
  public String titleForColumn(TableView tableView, int columnIndex) {
    if (tableView == this.weekendView) {
      return Weekday.values()[columnIndex].getRepresentation();
    }
    return String.format("Weekly Tasks: (%d)", this.taskQueue.size());
  }

  /**
   * get number of row for a specific column
   *
   * @param tableView   reference to delegator
   * @param columnIndex column index
   * @return number of element in this column
   */
  @Override
  public int numberOfRowFor(TableView tableView, int columnIndex) {
    if (tableView == this.weekendView) {
      return this.activities.get(Weekday.values()[columnIndex]).size();
    }
    return this.taskQueue.size();
  }

  /**
   * get the data of nth event/task on a specific day
   *
   * @param tableView   reference to delegator
   * @param columnIndex columnIndex of the table
   * @param rowIndex    rowIndex of the table
   * @return an instance of event/task
   */
  @Override
  public Activity getActivityForCellAt(TableView tableView, int columnIndex, int rowIndex) {
    if (tableView == this.weekendView) {
      return this.activities.get(Weekday.values()[columnIndex]).get(rowIndex);
    }
    return this.taskQueue.get(rowIndex);
  }

  /**
   * delegator calls the method when user clicks a cell at a specific index. delegatee handles the
   * user action
   *
   * @param tableView   reference to the delegator
   * @param columnIndex column index of the cell
   * @param rowIndex    row index of the cell
   */
  @Override
  public void didClickOn(TableView tableView, int columnIndex, int rowIndex) {
    Activity activity = this.getActivityForCellAt(tableView, columnIndex, rowIndex);
    Stage s = new Stage();
    MiniViewer miniViewer = new MiniViewer(activity, s);

    //set the action for the edit button on the mini viewer - will show the editable screen
    miniViewer.editSetOnAction(event -> {
      Stage stage = new Stage();
      Parent v = new ActivitySelectionView(activity, Settings.getInstance().getCategories(), this, stage);
      showPopup(this.mainStage, stage, v, "Edit");
      s.close();
    });

    //set the action for the delete button on the mini viewer
    miniViewer.deleteSetOnAction(event -> {
      this.model.removeActivity(activity);
      this.taskQueue = this.model.getTaskQueue(null);
      this.weekendView.reloadAll();
      this.taskQueueView.reloadAll();
      s.close();
    });

    //show the mini viewer in a pop up
    this.showPopup(this.mainStage, s, miniViewer, "Mini Viewer");
  }

  /**
   * submit the data to the delegatee for handling
   * @param formView reference to the formView
   * @param object the newly created object
   */
  @Override
  public void submit(FormView formView, Object object) {
    if (formView instanceof ActivitySelectionView) {
      Activity activity = (Activity) object;
      if (!Settings.getInstance().getCategories().contains(activity.getCategory())) {
        Settings.getInstance().getCategories()
            .add(new Category(activity.getCategory().getName(), null));
      }
      this.model.addActivity(activity);
      this.taskQueue = this.model.getTaskQueue(this.filterCategory);
      this.weekendView.reloadAll();
      //this.weekendView.reloadAt(activity.getWeekday().ordinal(),
          //this.activities.get(activity.getWeekday()).size() - 1);
      this.taskQueueView.reloadAll();
    }
    if(formView instanceof SettingsView){
      // TODO: update the starting week
    }
    this.showCommitmentWarning();
  }

  /**
   * Shows a commitment warning when user trys to add an event / task that exceeds the set max
   */
  private void showCommitmentWarning() {
    if (this.model.shouldDisplayCommitmentWarning()) {
      Utils.showAlert("Commitment Warning!", "haha");
    }
  }
}
