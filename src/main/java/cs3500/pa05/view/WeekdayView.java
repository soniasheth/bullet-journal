package cs3500.pa05.view;

import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Weekday;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WeekdayView extends HBox implements TableView {

  private WeekdayViewDelegate delegate;

  public WeekdayView(){
    super(100);
  }

  public void setDelegate(WeekdayViewDelegate delegate){
    this.delegate = delegate;
    this.getChildren().clear();
    List<Node> nodes = new ArrayList<>();
    for(Weekday weekday: Weekday.values()){
      nodes.add(this.renderColumn(weekday));
    }
    this.getChildren().addAll(nodes);
  }


  private VBox renderColumn(Weekday weekday){
    VBox ret = new VBox(50);
    List<Node> nodes = new ArrayList<>();
    nodes.add(new Label(weekday.getRepresentation()));
    for(int i = 0; i < this.delegate.numberOfActivitiesOnDay(weekday); i++){
      Activity data = this.delegate.dataForActivityOn(weekday, i);
      Button button = new Button(data.getName());
      this.delegate.setEventHandlerFor(button, weekday, i);
      nodes.add(button);
    }
    ret.getChildren().addAll(nodes);
    return ret;
  }


  @Override
  public void reloadAt(int index) throws IndexOutOfBoundsException {
    if(index < 0 || index >= Weekday.values().length){
      throw new IndexOutOfBoundsException("in WeekendView, index can only be [0, 7)!");
    }
    this.getChildren().set(index, this.renderColumn(Weekday.values()[index]));
  }
}
