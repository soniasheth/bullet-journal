package cs3500.pa05.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TaskQueueView extends GridPane implements TableView {

  private TableViewDelegate delegate;

  public TaskQueueView(){
    super();
    this.setPadding(new Insets(10));
    this.setHgap(10);
    this.setAlignment(Pos.CENTER);
  }

  @Override
  public void setDelegate(TableViewDelegate delegate) {
    this.delegate = delegate;
    this.reloadAll();
  }

  @Override
  public void reloadAll() {
    this.getChildren().clear();
    this.add(new Label(this.delegate.titleForColumn(this, 0)), 0, 0);
    for(int i = 0; i < this.delegate.numberOfRowFor(this, 0); i++){
      this.add(new ActivityView(this.delegate.dataForActivityOn(this, 0, i)), 0, i + 1);
    }
  }
}
