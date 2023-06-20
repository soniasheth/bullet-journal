package cs3500.pa05.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WelcomeView extends VBox {

    Button load;
    Button create;
    public WelcomeView() {
        //welcome label
        Label welcome = new Label("Welcome to your Bullet Journal!");
        welcome.setFont(Font.font("Bradley Hand", FontWeight.BOLD, 20));
        HBox welcomeText = new HBox(welcome);
        welcomeText.setAlignment(Pos.CENTER);

        //new button
        this.create = new Button("Create New +");
        this.create.setPrefSize(200, 100);
        this.create.setStyle("-fx-background-color: #8FBC8F;");

        //load in current bujo journal
        this.load = new Button("Upload +");
        this.load.setPrefSize(200, 100);
        this.load.setStyle("-fx-background-color: #8FBC8F;");

        //HBox for the buttons
        HBox options = new HBox(this.create, this.load);
        options.setSpacing(15);

        this.getChildren().addAll(welcomeText, options);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    public void setOnActionCreate(EventHandler<ActionEvent> createAction) {
        this.create.setOnAction(createAction);
    }

    public void setOnActionLoad(EventHandler<ActionEvent> loadAction) {
        this.load.setOnAction(loadAction);
    }


}
