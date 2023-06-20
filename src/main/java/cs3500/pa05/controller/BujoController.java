package cs3500.pa05.controller;

import cs3500.pa05.Utils;
import cs3500.pa05.model.*;
import cs3500.pa05.model.activities.Activity;
import cs3500.pa05.model.activities.Task;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.FormView;
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
 * represents the main controller class
 */
public class BujoController implements Controller, TableViewDelegate, FormDelegate {

  private final Stage mainStage;
  private final WeekdaysModel model;
  private final Map<Weekday, List<Activity>> activities;
  private List<Task> taskQueue;
  private final Category filterCategory = null;
  private final TableView weekendView;
  private final TableView taskQueueView;

  public BujoController(Stage mainStage, WeekdaysModel model, TableView weekendView,
                        TableView taskQueueView, ActivitiesButtons activities, Button settings, Button eventStats, Button taskStats) {
    this.mainStage = mainStage;
    this.model = model;
    this.activities = this.model.getActivities(this.filterCategory);
    this.taskQueue = this.model.getTaskQueue(this.filterCategory);
    this.weekendView = weekendView;
    this.weekendView.setDelegate(this);
    this.taskQueueView = taskQueueView;
    this.taskQueueView.setDelegate(this);
    handleActivities(activities);
    handleSettings(settings);

    handleEventStats(eventStats);
    handleTaskStats(taskStats);

  }

  public void handleActivities(ActivitiesButtons activities) {
    //handle pop up for pushing the buttons creating new activities
    activities.setOnActionEventButton(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s, new ActivitySelectionView(ActivityType.EVENT,
          Settings.getInstance().getCategories(), this, s), "New Event");
    });
    activities.setOnActionTaskButton(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s, new ActivitySelectionView(ActivityType.TASK,
          Settings.getInstance().getCategories(), this, s), "New Task");
    });
  }

  public void handleEventStats(Button b) {
    b.setOnAction(event -> {
      Stage s = new Stage();
      WeeklyStat stats = new WeeklyStat(model);
      this.showPopup(this.mainStage, s, new WeeklyStatsView(stats, ActivityType.EVENT), "Event Stats");
    });
  }

  public void handleTaskStats(Button b) {
    b.setOnAction(event -> {
      Stage s = new Stage();
      WeeklyStat stats = new WeeklyStat(model);
      this.showPopup(this.mainStage, s, new WeeklyStatsView(stats, ActivityType.TASK), "Task Stats");
    });
  }

  public void handleSettings(Button settings) {
    //handles pop up when pushing the settings button
    settings.setOnAction(event -> {
      Stage popup = new Stage();
      VBox settingsView = new SettingsView(Settings.getInstance(), false, this, popup);
      this.showPopup(this.mainStage, popup, settingsView, "Settings");
    });
  }

  public void welcome() {
    Stage popup = new Stage();
    WelcomeView welcomeView = new WelcomeView();
    welcomeView.setOnActionCreate(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s,
              new SettingsView(Settings.getInstance(), true, this, popup), "New Journal");
    });
    this.showPopup(this.mainStage, popup, welcomeView, "Welcome!");
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
    Parent v = new ActivitySelectionView(activity, Settings.getInstance().getCategories(), this, s);
    this.showPopup(this.mainStage, s, v, "Advanced Mini Viewer");
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
    this.showCommitmentWarning();
  }

  private void showPopup(Stage ownerStage, Stage popupStage, Parent popUp, String title) {
    popupStage.initOwner(ownerStage);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.setTitle(title);
    Scene popupScene = new Scene(popUp);
    popupStage.setScene(popupScene);
    popupStage.showAndWait();
  }

  private void showCommitmentWarning() {
    if (this.model.shouldDisplayCommitmentWarning()) {
      Utils.showAlert("Commitment Warning!", "haha");
    }
  }
}
