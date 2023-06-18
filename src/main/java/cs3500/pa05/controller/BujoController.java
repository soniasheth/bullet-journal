package cs3500.pa05.controller;

import cs3500.pa05.model.*;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.SettingsView;
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

  private Stage mainStage;
  private WeekdayModel model;
  private Map<Weekday, List<Activity>> activities;
  private List<Activity> taskQueue;
  private Category filterCategory = null;
  private TableView weekendView;
  private TableView taskQueueView;

  public BujoController(Stage mainStage, WeekdayModel model, TableView weekendView,
      TableView taskQueueView, ActivitiesButtons activities, Button settings) {
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
  }

  public void handleActivities(ActivitiesButtons activities) {
    //handle pop up for pushing the buttons creating new activities
    activities.setOnActionEventButton(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s, new ActivitySelectionView(ActivityType.EVENT,
          this.model.getCategories(), this, s), "New Event");
    });
    activities.setOnActionTaskButton(event -> {
      Stage s = new Stage();
      this.showPopup(this.mainStage, s, new ActivitySelectionView(ActivityType.TASK,
          this.model.getCategories(), this, s), "New Task");
    });
  }

  public void handleSettings(Button settings) {
    //handles pop up when pushing the settings button
    settings.setOnAction(event -> {
      Stage popup = new Stage();
      Settings settings1 = new Settings();
      VBox settingsView = new SettingsView(settings1, false, popup);
      this.showPopup(this.mainStage, popup, settingsView, "Settings");
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
    return String.format("your queue: (%d)", this.taskQueue.size());
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
  public Activity dataForActivityOn(TableView tableView, int columnIndex, int rowIndex) {
    if (tableView == this.weekendView) {
      return this.activities.get(Weekday.values()[columnIndex]).get(rowIndex);
    }
    return this.taskQueue.get(rowIndex);
  }

  @Override
  public void submit(Activity activity) {
    if (!this.model.getCategories().contains(activity.getCategory())) {
      this.model.getCategories().add(new Category(activity.getCategory().getName(), null));
    }
    this.model.addActivity(activity);
    this.taskQueue = this.model.getTaskQueue(this.filterCategory);
    this.weekendView.reloadAt(activity.getWeekday().ordinal(),
        this.activities.get(activity.getWeekday()).size() - 1);
    this.taskQueueView.reloadAll();

  }

  private void showPopup(Stage ownerStage, Stage popupStage, Parent popUp, String title) {
    //Stage popupStage = new Stage();
    popupStage.initOwner(ownerStage);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.setTitle(title);
    Scene popupScene = new Scene(popUp);
    popupStage.setScene(popupScene);
    popupStage.showAndWait();
  }

}
