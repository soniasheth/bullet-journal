package cs3500.pa05.controller;

import cs3500.pa05.model.*;
import cs3500.pa05.model.enums.ActivityType;
import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.activities.ActivitySelectionView;
import cs3500.pa05.view.activities.AddButton;
import cs3500.pa05.view.delegates.FormDelegate;
import cs3500.pa05.view.tables.TableView;
import cs3500.pa05.view.delegates.TableViewDelegate;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * represents the main controller class
 */
public class BujoController implements Controller, TableViewDelegate, FormDelegate {

  private Stage mainStage;
  private WeekdayModel model;
  private List<Activity> taskQueue;
  private TableView weekendView;
  private TableView taskQueueView;

  public BujoController(Stage mainStage, WeekdayModel model, TableView weekendView, TableView taskQueueView, AddButton activities) {
    this.mainStage = mainStage;
    this.model = model;
    this.taskQueue = new ArrayList<>(this.model.getTaskQueue());
    this.weekendView = weekendView;
    this.weekendView.setDelegate(this);
    this.taskQueueView = taskQueueView;
    this.taskQueueView.setDelegate(this);

    activities.setOnActionEventButton(event -> this.showPopup(this.mainStage, ActivityType.EVENT));
    activities.setOnActionTaskButton(event -> this.showPopup(this.mainStage, ActivityType.TASK));

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
    if(tableView == this.weekendView){
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
      return this.model.getActivitiesFor(Weekday.values()[columnIndex]).size();
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
      return this.model.getActivitiesFor(Weekday.values()[columnIndex]).get(rowIndex);
    }
    return this.taskQueue.get(rowIndex);
  }

  @Override
  public void submit(Activity activity) {
    if(!this.model.getCategories().contains(activity.getCategory())){
      this.model.getCategories().add(new Category(activity.getCategory().getName(), null));
    }
    this.model.addActivity(activity);
    this.taskQueue = new ArrayList<>(this.model.getTaskQueue());
    this.weekendView.reloadAt(activity.getWeekday().ordinal(), this.model.getActivitiesFor(activity.getWeekday()).size() - 1);
    this.taskQueueView.reloadAll();

  private void showPopup(Stage ownerStage) {
    Stage popupStage = new Stage();
    popupStage.initOwner(ownerStage);
    popupStage.initModality(Modality.APPLICATION_MODAL);
    //popupStage.setTitle("Popup Window");
    VBox newActivityView = new ActivitySelectionView(ActivityType.TASK, this.model.getCategories(), this, popupStage);

    Scene popupScene = new Scene(newActivityView);
    popupStage.setScene(popupScene);
    popupStage.showAndWait();
  }

}
