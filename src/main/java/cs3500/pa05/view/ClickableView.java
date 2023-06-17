package cs3500.pa05.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface ClickableView extends EventHandler {
  void setOnAction(EventHandler<ActionEvent> eventEventHandler);
}
