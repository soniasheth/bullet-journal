package cs3500.pa05.view;

public interface TableView {
  void setDelegate(TableViewDelegate delegate);
  default void reloadAt(int colIndex, int rowIndex) throws IllegalArgumentException{
    // does nothing. by default the view doesn't need to reload
  }

  default void reloadAll(){
    // does nothing. by default the view doesn't need to reload
  }


}
