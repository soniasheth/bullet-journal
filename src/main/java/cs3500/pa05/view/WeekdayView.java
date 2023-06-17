package cs3500.pa05.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class WeekdayView extends GridPane implements TableView {

  private TableViewDelegate delegate;

  public WeekdayView() {
    super();
    this.setPadding(new Insets(10));
    this.setHgap(10);
    this.setAlignment(Pos.CENTER);
  }

  @Override
  public void setDelegate(TableViewDelegate delegate) {
    this.delegate = delegate;
    this.getChildren().clear();
    for(int i = 0; i < this.delegate.numberOfColumns(this); i++){
      this.add(new Label(this.delegate.titleForColumn(this, i)), i, 0);
      for(int j = 0; j < this.delegate.numberOfRowFor(this, i); j++){
        this.renderCell(i, j);
      }
    }
  }


  private void renderCell(int colIndex, int rowIndex) {
    ActivityView v = new ActivityView(this.delegate.dataForActivityOn(this,
        colIndex, rowIndex));
    this.add(v, colIndex, rowIndex + 1);
  }


  @Override
  public void reloadAt(int colIndex, int rowIndex) throws IllegalArgumentException {
    if(colIndex < 0 || colIndex >= this.delegate.numberOfColumns(this) || rowIndex < 0
        || rowIndex >= this.delegate.numberOfRowFor(this, colIndex)){
      throw new IllegalArgumentException("given indices are out of bounds!");
    }
    this.renderCell(colIndex, rowIndex);
  }
}
