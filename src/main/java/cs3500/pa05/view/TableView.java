package cs3500.pa05.view;

public interface TableView {
  void reloadAt(int colIndex, int rowIndex) throws IllegalArgumentException;
}
