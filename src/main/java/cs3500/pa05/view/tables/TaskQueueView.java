package cs3500.pa05.view.tables;

import cs3500.pa05.view.ActivityView;
import cs3500.pa05.view.delegates.TableViewDelegate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * represents the task queue view
 */
public class TaskQueueView extends GridPane implements TableView {

  /**
   * delegate for this task queue
   */
  private TableViewDelegate delegate;

  /**
   * default constructor
   */
  public TaskQueueView() {
    super();
    this.setPadding(new Insets(10));
    this.setHgap(10);
    this.setAlignment(Pos.CENTER);
  }

  /**
   * set the delegate for table view
   *
   * @param delegate a delegate that provide data to the table view
   */
  @Override
  public void setDelegate(TableViewDelegate delegate) {
    this.delegate = delegate;
    this.reloadAll();
  }

  /**
   * delegatee calls this method to reload all cells in the table view
   */
  @Override
  public void reloadAll() {
    this.getChildren().clear();
    this.add(new Label(this.delegate.titleForColumn(this, 0)), 0, 0);
    for (int i = 0; i < this.delegate.numberOfRowFor(this, 0); i++) {
      this.add(new ActivityView(this.delegate.dataForActivityOn(this, 0, i)), 0, i + 1);
    }
  }
}
