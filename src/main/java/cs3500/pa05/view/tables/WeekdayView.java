package cs3500.pa05.view.tables;

import cs3500.pa05.model.enums.Weekday;
import cs3500.pa05.view.ActivityView;
import cs3500.pa05.view.delegates.TableViewDelegate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * represents a weekday view class
 */
public class WeekdayView extends GridPane implements TableView {

  private final int columnWidth = 200;
  /**
   * delegate for the delegate
   */
  private TableViewDelegate delegate;

  /**
   * default constructor
   */
  public WeekdayView() {
    super();
    this.setPadding(new Insets(10));
    this.setHgap(10);
    this.setAlignment(Pos.CENTER);
    for(int i = 0; i < Weekday.values().length; i++){
      ColumnConstraints cons = new ColumnConstraints();
      cons.setPrefWidth(columnWidth);
      this.getColumnConstraints().add(cons);
    }
    CornerRadii cornerRadii = new CornerRadii(7);
    BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
            cornerRadii, new BorderWidths(1));
    Border border = new Border(borderStroke);
    this.setBorder(border);
  }

  /**
   * set the delegate for table view
   *
   * @param delegate a delegate that provide data to the table view
   */
  @Override
  public void setDelegate(TableViewDelegate delegate) {
    this.delegate = delegate;
    this.reloadAll();//
  }

  /**
   * helper method to render a specific cell
   *
   * @param colIndex column index of the cell
   * @param rowIndex row index of the cell
   */
  private void renderCell(int colIndex, int rowIndex) {
    ActivityView v = new ActivityView(this.delegate.dataForActivityOn(this,
        colIndex, rowIndex));
    this.add(v, colIndex, rowIndex + 1);
  }

  /**
   * delegatee calls this method to reload a specific cell in the table view
   *
   * @param colIndex column index of the cell
   * @param rowIndex row index of the cell
   * @throws IllegalArgumentException if user provides invalid indices
   */
  @Override
  public void reloadAt(int colIndex, int rowIndex) throws IllegalArgumentException {
    if (colIndex < 0 || colIndex >= Weekday.values().length || rowIndex < 0
        || rowIndex >= this.delegate.numberOfRowFor(this, colIndex)) {
      throw new IllegalArgumentException("given indices are out of bounds!");
    }
    this.renderCell(colIndex, rowIndex);
  }

  /**
   * delegatee calls this method to reload all cells in the table view
   */
  @Override
  public void reloadAll() {
    this.getChildren().clear();
    for (int i = 0; i < Weekday.values().length; i++) {
      Label l = new Label(this.delegate.titleForColumn(this, i));
      //l.setAlignment(Pos.CENTER);
      l.setPrefWidth(columnWidth);

      l.setFont(Font.font("Bradley Hand", FontWeight.BOLD, 20));
      this.add(l, i, 0);
      for (int j = 0; j < this.delegate.numberOfRowFor(this, i); j++) {
        this.renderCell(i, j);
      }
    }
  }
}
