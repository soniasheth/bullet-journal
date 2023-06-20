package cs3500.pa05.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WelcomeView extends VBox {

    Button load;
    Button create;
    public WelcomeView() {
        //welecome label
        Label welcome = new Label("Welcome to your Bullet Journal!");
        welcome.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        HBox welcomeText = new HBox(welcome);

        //new button
        this.create = new Button("Create New +");
        this.create.setPrefSize(200, 100);

        //load in current bujo journal
        this.load = new Button("Upload +");
        this.load.setPrefSize(200, 100);

        //HBox for the buttons
        HBox options = new HBox(this.create, this.load);
        options.setSpacing(15);

        this.getChildren().addAll(welcome, welcomeText, options);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    public void setOnActionCreate(EventHandler<ActionEvent> createAction) {
        this.create.setOnAction(createAction);
        System.out.println("Called!");
    }

    public void setOnActionLoad(EventHandler<ActionEvent> loadAction) {
        this.load.setOnAction(loadAction);
    }


}
